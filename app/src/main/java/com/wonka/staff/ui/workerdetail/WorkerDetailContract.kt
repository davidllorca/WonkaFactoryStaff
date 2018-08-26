package com.wonka.staff.ui.workerdetail

import com.wonka.staff.ui.base.BaseContract

/**
 * Contract between the View and the Presenter.
 */
interface WorkerDetailContract {

    interface View : BaseContract.View<WorkerDetailViewState>

    interface Presenter : BaseContract.Presenter<View> {

        fun loadWorkerDetail(id: Int)

    }
}