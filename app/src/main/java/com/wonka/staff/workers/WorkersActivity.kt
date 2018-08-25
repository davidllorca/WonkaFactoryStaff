package com.wonka.staff.workers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wonka.staff.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class WorkersActivity : AppCompatActivity(), WorkersContract.View {

    @Inject
    lateinit var presenter: WorkersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.dropView()
    }

    override fun render(state: WorkersViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
