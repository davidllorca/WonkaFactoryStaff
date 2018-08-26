package com.wonka.staff.ui.workerdetail

import android.util.Log
import com.wonka.staff.domain.GetWorkerDetailUseCase
import com.wonka.staff.ui.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@ActivityScope
class WorkerDetailPresenter @Inject constructor(
        private val useCase: GetWorkerDetailUseCase
) : WorkerDetailContract.Presenter {

    override var mView: WorkerDetailContract.View? = null
    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun loadWorkerDetail(id: Int) {
        add(useCase.execute(GetWorkerDetailUseCase.Params(id))
                .subscribe({ worker -> mView?.renderViewSate(WorkerDetailViewState()) },
                        { error -> Log.e("WorkerDetailPresenter", "Error getting details", error) }))
    }

}