package com.wonka.staff.ui.workers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.wonka.staff.R
import com.wonka.staff.ui.base.ImageLoader
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.content_workers.*
import javax.inject.Inject

class WorkersActivity : AppCompatActivity(), WorkersContract.View {

    @Inject
    lateinit var presenter: WorkersContract.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)

        rv_workers_list.let {
            it.layoutManager = GridLayoutManager(this, 2)
            it.adapter = WorkersAdapter(imageLoader)
        }
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
            }
            is WorkersViewState.Results -> {
                (rv_workers_list.adapter as WorkersAdapter).init(state.workers)
            }
            is WorkersViewState.Error -> {
                Toast.makeText(this, state.error.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
