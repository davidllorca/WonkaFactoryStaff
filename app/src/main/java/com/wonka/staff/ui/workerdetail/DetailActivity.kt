package com.wonka.staff.ui.workerdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wonka.staff.R
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), WorkerDetailContract.View {

    @Inject
    lateinit var presenter: WorkerDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.loadWorkerDetail(1) // TODO remind changing it
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun render(state: WorkerDetailViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
