package com.wonka.staff.data

import com.wonka.staff.data.remote.WorkerEntity
import io.reactivex.Single

interface WorkersDataSource {

    fun getWorkers(): Single<List<WorkerEntity>>

    fun getWorker(id: Int): Single<WorkerEntity>

}