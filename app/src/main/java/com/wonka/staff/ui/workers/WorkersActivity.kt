package com.wonka.staff.ui.workers

import android.os.Bundle
import android.support.design.chip.Chip
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
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

    lateinit var bottomSheetBehavior: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)

        rv_workers_list.let {
            it.layoutManager = GridLayoutManager(this, N_GRID_COLUMNS)
            it.adapter = WorkersAdapter(imageLoader, this)

            val scrollListener = object : EndlessRecyclerViewScrollListener(it.layoutManager!!) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    loadNextData(page)
                }
            }
            it.addOnScrollListener(scrollListener)
        }
        bottomSheetBehavior = BottomSheetBehavior.from(layout_bottom_sheet)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Log.d("Filter", "$newState")
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        applyFilters()
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
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
        (rv_workers_list.adapter as WorkersAdapter).applyFilters(getActiveFilters())
    }

    private fun getActiveFilters(): WorkersAdapter.FiltersState {
        val extractState: (Chip) -> Pair<String, Boolean> = { it.text.toString() to it.isChecked }

        return WorkersAdapter.FiltersState(
                mapOf(
                        with(chip_filter_male) { extractState(this) },
                        with(chip_filter_female) { extractState(this) },
                        with(chip_filter_developer) { extractState(this) },
                        with(chip_filter_metal_worker) { extractState(this) },
                        with(chip_filter_gemcutter) { extractState(this) },
                        with(chip_filter_medic) { extractState(this) },
                        with(chip_filter_brewer) { extractState(this) }))
    }

    private fun loadNextData(page: Int) { // TODO REMOVE PAGE?
        //presenter.loadWorkers()
    }

    override fun onStart() {
        super.onStart()
        pb_workers.visibility = View.VISIBLE // MOVE TO VIEW INTERFACE
        presenter.loadWorkers()

    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun renderViewSate(state: WorkersViewState) {
        when (state) {
            is WorkersViewState.Loading -> {
                pb_workers.visibility = View.VISIBLE
            }
            is WorkersViewState.Results -> {
                pb_workers.visibility = View.GONE
                (rv_workers_list.adapter as WorkersAdapter).appendAll(state.workers)
            }
            is WorkersViewState.Error -> {
                pb_workers.visibility = View.GONE
                Toast.makeText(this, state.error.message, Toast.LENGTH_LONG).show()
            }
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
