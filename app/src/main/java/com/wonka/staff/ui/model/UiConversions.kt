package com.wonka.staff.ui.model

import com.wonka.staff.domain.model.Favorite
import com.wonka.staff.domain.model.Worker
import com.wonka.staff.domain.model.WorkerDetail
import com.wonka.staff.ui.workerdetail.WorkerDetailContract
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

fun WorkerDetail.toWorkerDetailView(): WorkerDetailContract.WorkerDetailViewObject =
        WorkerDetailViewImpl(
                "${this.firstName} ${this.lastName}",
                this.gender,
                this.profession,
                this.image,
                this.description,
                this.quota,
                this.height,
                this.country,
                this.age,
                this.favorite.toFavoriteView(),
                this.email)

fun Favorite.toFavoriteView(): WorkerDetailContract.WorkerDetailViewObject.FavoriteViewObject =
        FavoriteViewImpl(this.color,
                this.food,
                this.randomString,
                this.song)

// Implementation of required View objects abstractions.

data class WorkerViewImpl(override val id: Int,
                          override val name: String,
                          override val gender: String,
                          override val profession: String,
                          override val avatarUrl: String) : WorkersContract.WorkerViewObject

data class WorkerDetailViewImpl(override val name: String,
                                override val gender: String,
                                override val profession: String,
                                override val avatarUrl: String,
                                override val description: String,
                                override val quota: String,
                                override val height: Int,
                                override val country: String,
                                override val age: Int,
                                override val favorite: WorkerDetailContract.WorkerDetailViewObject.FavoriteViewObject,
                                override val email: String) : WorkerDetailContract.WorkerDetailViewObject

data class FavoriteViewImpl(override val color: String,
                            override val food: String,
                            override val randomString: String,
                            override val song: String) : WorkerDetailContract.WorkerDetailViewObject.FavoriteViewObject




