package com.wonka.staff.data

import com.wonka.staff.data.remote.RemoteDataSource
import com.wonka.staff.data.remote.WorkerData
import com.wonka.staff.data.remote.WorkerDetailData
import com.wonka.staff.domain.model.Worker
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : WorkersDataSource {

    val cache: Map<Int, Worker> = HashMap()

    override fun getWorkers(): Single<List<WorkerData>> {
        return remoteDataSource.getWorkers()
    }

    override fun getWorker(id: Int): Single<WorkerDetailData> {
        return remoteDataSource.getWorker(id)
    }

}