package com.wonka.staff.ui.workerdetail

import android.util.Log
import com.wonka.staff.domain.GetWorkerDetailUseCase
import com.wonka.staff.ui.di.ActivityScope
import com.wonka.staff.ui.model.toWorkerDetailView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


@ActivityScope
class WorkerDetailPresenter @Inject constructor(
        private val useCase: GetWorkerDetailUseCase
) : WorkerDetailContract.Presenter {

    override var mView: WorkerDetailContract.View? = null
    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private lateinit var mViewState: WorkerDetailViewState

    override fun loadWorkerDetail(id: Int) {
        add(useCase.execute(GetWorkerDetailUseCase.Params(id))
                .doOnSubscribe {
                    mViewState = WorkerDetailViewState.Loading()
                    renderState()
                }
                .doAfterTerminate { renderState() }
                .subscribe({ result ->
                    mViewState = WorkerDetailViewState.Result(result.worker.toWorkerDetailView())
                }, { error ->
                    mViewState = WorkerDetailViewState.Error(error)
                    Log.e("WorkerDetailPresenter", "Error getting details", error) }))
    }

    private fun renderState() {
        mView?.renderViewSate(mViewState)
    }

}