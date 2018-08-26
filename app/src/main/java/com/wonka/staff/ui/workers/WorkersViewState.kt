package com.wonka.staff.ui.workers

import com.wonka.staff.domain.model.Worker

sealed class WorkersViewState {
    class Loading : WorkersViewState()
    class Results(val workers: List<Worker>) : WorkersViewState()
    class Error(val error: Throwable) : WorkersViewState() // TODO obfuscate this
}
