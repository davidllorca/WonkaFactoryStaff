package com.wonka.staff.ui.workerdetail

import android.util.Log
import com.wonka.staff.domain.GetWorkerUseCase
import com.wonka.staff.ui.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@ActivityScope
class WorkerDetailPresenter @Inject constructor(
        private val useCase: GetWorkerUseCase
) : WorkerDetailContract.Presenter {

    override var mView: WorkerDetailContract.View? = null
    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadWorkerDetail(id: Int) {
        add(useCase.execute(GetWorkerUseCase.Params(id))
                .subscribe({ worker -> mView?.render(WorkerDetailViewState()) },
                        { error -> Log.e("WorkerDetailPresenter", "Error getting details", error) }))
    }

}