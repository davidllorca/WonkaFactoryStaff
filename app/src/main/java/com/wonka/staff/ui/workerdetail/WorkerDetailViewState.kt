package com.wonka.staff.ui.workerdetail

sealed class WorkerDetailViewState {
    class Loading : WorkerDetailViewState()
    class Results(val workerDetail: WorkerDetailContract.WorkerDetailViewObject) : WorkerDetailViewState()
    class Error(val error: Throwable) : WorkerDetailViewState()
}
