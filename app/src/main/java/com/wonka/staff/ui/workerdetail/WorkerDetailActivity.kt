package com.wonka.staff.ui.workerdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wonka.staff.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class WorkerDetailActivity : AppCompatActivity(), WorkerDetailContract.View {

    @Inject
    lateinit var presenter: WorkerDetailContract.Presenter

    private var targetWorkerId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        targetWorkerId = intent.getIntExtra(EXTRA_WORK_ID, -1)
        if (targetWorkerId == -1) {
            throw IllegalArgumentException("${WorkerDetailActivity::class.simpleName} need a worker id to be initialized")
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.loadWorkerDetail(targetWorkerId) // TODO remind changing it
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun renderViewSate(state: WorkerDetailViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        private const val EXTRA_WORK_ID: String = "worker_id"

        /**
         * Prepares Intent with id of worker to show as argument.
         */
        fun getCallingIntent(context: Context, id: Int): Intent {
            return Intent(context, WorkerDetailActivity::class.java)
                    .apply { putExtra(EXTRA_WORK_ID, id) }
        }
    }
}
