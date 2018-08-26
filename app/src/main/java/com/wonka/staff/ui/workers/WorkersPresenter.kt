package com.wonka.staff.ui.workers

import android.util.Log
import com.wonka.staff.domain.GetWorkersUseCase
import com.wonka.staff.ui.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class WorkersPresenter @Inject constructor(
        private val userCase: GetWorkersUseCase
) : WorkersContract.Presenter {

    override var mView: WorkersContract.View? = null
    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadWorkers() {
        add(userCase.execute(GetWorkersUseCase.Params(1))
                .subscribe({ t1, t2 -> Log.d("Workers", "$t1 /// ${t1.workers}") }))
        mView?.render(WorkersViewState()) // TODO remind implement this
    }


}

