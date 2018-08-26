package com.wonka.staff.ui.workerdetail

import com.wonka.staff.ui.base.BaseContract

interface WorkerDetailContract {

    interface View : BaseContract.View<WorkerDetailViewState>

    interface Presenter {

        fun loadWorkerDetail(id: Int)

    }
}