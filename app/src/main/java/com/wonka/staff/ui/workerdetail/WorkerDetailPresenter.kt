package com.wonka.staff.ui.workerdetail

import android.util.Log
import com.wonka.staff.domain.GetWorkerUseCase
import com.wonka.staff.ui.base.BasePresenter
import com.wonka.staff.ui.di.ActivityScope
import javax.inject.Inject


@ActivityScope
class WorkerDetailPresenter @Inject constructor(
        private val useCase: GetWorkerUseCase
) : BasePresenter<WorkerDetailContract.View>(), WorkerDetailContract.Presenter {

    override fun loadWorkerDetail(id: Int) {
        add(useCase.execute(GetWorkerUseCase.Params(id))
                .subscribe({ worker -> mView?.render(WorkerDetailViewState()) },
                        { error -> Log.e("WorkerDetailPresenter", "Error getting details", error) }))
    }

}