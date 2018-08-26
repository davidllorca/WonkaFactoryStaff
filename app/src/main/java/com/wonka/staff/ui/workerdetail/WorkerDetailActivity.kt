package com.wonka.staff.ui.workerdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.wonka.staff.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
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
        when (state) {
            is WorkerDetailViewState.Loading -> {
                //pb_workers.visibility = View.VISIBLE TODO
            }
            is WorkerDetailViewState.Result -> {
                with(state.workerDetail) {
                    tv_detail_description.text = this.description
                    tv_detail_quota.text = this.quota
                    tv_detail_profession.text = this.profession
                    tv_detail_height.text = this.height.toString()
                    tv_detail_country.text = this.country
                    tv_detail_age.text = this.age.toString()
                    tv_detail_gender.text = this.gender
                    tv_detail_email.text = this.email
                    // TODO display favorite
                }
            }
            is WorkerDetailViewState.Error -> {
                Toast.makeText(this, state.error.message, Toast.LENGTH_LONG).show()
            }
        }
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
