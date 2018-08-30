package com.wonka.staff.ui.workers

sealed class WorkersViewState {
    class Loading : WorkersViewState()
    class Results(val workers: List<WorkersContract.WorkerViewObject>) : WorkersViewState()
    class Error(val error: Throwable) : WorkersViewState() // TODO obfuscate this
}
