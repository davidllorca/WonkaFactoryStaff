package com.wonka.staff.ui.workers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.wonka.staff.R
import com.wonka.staff.ui.base.EndlessRecyclerViewScrollListener
import com.wonka.staff.ui.base.ImageLoader
import com.wonka.staff.ui.workerdetail.WorkerDetailActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_workers.*
import kotlinx.android.synthetic.main.content_workers.*
import javax.inject.Inject

class WorkersActivity : AppCompatActivity(), WorkersContract.View, WorkersAdapter.Listener {

    @Inject
    lateinit var presenter: WorkersContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    // Implementation of ScrollListener with loading callback events.

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
    }

    private fun loadNextData(page: Int) {
        presenter.loadWorkers()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.loadWorkers()
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
