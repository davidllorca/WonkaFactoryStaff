package com.wonka.staff.ui.model

import com.wonka.staff.domain.model.Worker
import com.wonka.staff.ui.workers.WorkersContract

/**
 * Conversion utilities from domain layer models to UI layer models.
 */

fun Iterable<Worker>.toWorkersView(): List<WorkersContract.WorkerViewObject> = map {
    WorkerViewImpl(
            it.id,
            "${it.firstName} ${it.lastName}",
            it.gender,
            it.profession,
            it.image)
}

// Implementation of required View objects abstractions.

data class WorkerViewImpl(override val id: Int,
                          override val name: String,
                          override val gender: String,
                          override val profession: String,
                          override val avatarUrl: String): WorkersContract.WorkerViewObject




