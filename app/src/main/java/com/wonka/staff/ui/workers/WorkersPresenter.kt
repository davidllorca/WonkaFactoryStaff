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
                .subscribe({ result -> mView?.render(WorkersViewState.Results(result.workers)) },
                        { error ->
                            mView?.render(WorkersViewState.Error(error))
                            Log.e("WorkerPresenter", "Error getting workers list", error)
                        }))
    }


}

