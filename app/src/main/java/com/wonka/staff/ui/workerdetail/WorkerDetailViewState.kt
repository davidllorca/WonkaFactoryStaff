package com.wonka.staff.ui.workerdetail

import com.wonka.staff.domain.model.WorkerDetail

sealed class WorkerDetailViewState {
    class Loading(val isLoading: Boolean) : WorkerDetailViewState()
    class Result(val workerDetail: WorkerDetail) : WorkerDetailViewState()
    class Error(val error: Throwable) : WorkerDetailViewState() // TODO obfuscate this
}
