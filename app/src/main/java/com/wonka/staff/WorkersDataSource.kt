package com.wonka.staff

import io.reactivex.Single

interface WorkersDataSource {

    fun getWorkers(): Single<List<WorkerEntity>>

    fun getWorker(id: Int): Single<WorkerEntity>

}