package com.wonka.staff.ui.workers

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import com.wonka.staff.R
import com.wonka.staff.ui.base.EndlessRecyclerViewScrollListener
import com.wonka.staff.ui.base.ImageLoader
import com.wonka.staff.ui.workerdetail.WorkerDetailActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_workers.*
import kotlinx.android.synthetic.main.bottom_sheets_workers_filter.*
import kotlinx.android.synthetic.main.content_workers.*
import javax.inject.Inject


class WorkersActivity : AppCompatActivity(), WorkersContract.View, WorkersAdapter.Listener {

    @Inject
    lateinit var presenter: WorkersContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    lateinit var adapter: WorkersAdapter
    lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)

        adapter = WorkersAdapter(imageLoader, this)
        rv_workers_list.let {
            it.layoutManager = GridLayoutManager(this, N_GRID_COLUMNS)
            it.adapter = adapter

            val scrollListener = object : EndlessRecyclerViewScrollListener(it.layoutManager!!) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    loadNextData()
                }
            }
            it.addOnScrollListener(scrollListener)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(layout_bottom_sheet)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.d("Filter", "$newState")
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        applyFilters()
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // no-op
            }
        })

    }

    fun openFilterOptions(view: View) {
        Log.d("Filter onClick", "${bottomSheetBehavior.state}")
        when (bottomSheetBehavior.state) { // This is the state before clicking
            STATE_EXPANDED, STATE_DRAGGING -> {
                bottomSheetBehavior.state = STATE_COLLAPSED
            }
            STATE_COLLAPSED, STATE_HIDDEN -> {
                bottomSheetBehavior.state = STATE_EXPANDED
            }
        }
    }

    private fun applyFilters() {
        var filters = getCurrentFilters()
        adapter.applyFilters(filters)
    }

    private fun getCurrentFilters(): WorkersAdapter.FiltersState {
        val extractState: (CheckBox) -> Pair<String, Boolean> = { it.tag.toString() to it.isChecked }

        return WorkersAdapter.FiltersState(
                // Gender options
                mapOf(
                        with(chip_filter_male) { extractState(this) },
                        with(chip_filter_female) { extractState(this) }),
                // Profession options
                mapOf(
                        with(chip_filter_developer) { extractState(this) },
                        with(chip_filter_metal_worker) { extractState(this) },
                        with(chip_filter_gemcutter) { extractState(this) },
                        with(chip_filter_medic) { extractState(this) },
                        with(chip_filter_brewer) { extractState(this) }))
    }

    private fun loadNextData() { // TODO REMOVE PAGE?
        // If user is user filter options pagination will be paused
        if (!adapter.isFiltered) {
            presenter.loadWorkers()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        if(adapter.isInitialized) {
            loadNextData()
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun renderViewSate(state: WorkersViewState) {
        when (state) {
            is WorkersViewState.Loading -> {
                showLoadingVisibility(true)
            }
            is WorkersViewState.Results -> {
                showLoadingVisibility(false)
                (rv_workers_list.adapter as WorkersAdapter).appendAll(state.workers)
            }
            is WorkersViewState.Error -> {
                showLoadingVisibility(false)
                Toast.makeText(this, state.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showLoadingVisibility(visible: Boolean) {
        pb_workers.visibility.let {
           if(visible) View.VISIBLE else View.GONE
        }
    }

    override fun onClickWorker(id: Int) {
        WorkerDetailActivity.getCallingIntent(this, id)
                .let { startActivity(it) }
    }

    companion object {
        private const val N_GRID_COLUMNS = 2
    }
}
