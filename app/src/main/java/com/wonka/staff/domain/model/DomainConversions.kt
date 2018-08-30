package com.wonka.staff.domain.model

import com.wonka.staff.data.remote.FavoriteData
import com.wonka.staff.data.remote.WorkerData
import com.wonka.staff.data.remote.WorkerDetailData

/**
 *  Conversion utilities from data layer models to domain layer models.
 */
fun Iterable<WorkerData>.toWorkers(): List<Worker> = map {
    Worker(
            it.id,
            it.firstName,
            it.lastName,
            it.image,
            it.profession,
            it.height,
            it.country,
            it.age,
            it.favorite.toFavourite(),
            it.gender,
            it.email)
}

fun FavoriteData.toFavourite(): Favorite =
        Favorite(this.color, this.food, this.randomString, this.song)

fun WorkerDetailData.toWorkerDetail(): WorkerDetail =
        WorkerDetail(
                this.firstName,
                this.lastName,
                this.description,
                this.quota,
                this.image,
                this.profession,
                this.height,
                this.country,
                this.age,
                this.favorite.toFavourite(),
                this.gender,
                this.email)
