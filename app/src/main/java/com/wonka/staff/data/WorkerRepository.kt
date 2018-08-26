package com.wonka.staff.data

import com.wonka.staff.data.remote.RemoteDataSource
import com.wonka.staff.data.remote.WorkerData
import com.wonka.staff.data.remote.WorkerDetailData
import com.wonka.staff.domain.model.Worker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : WorkersDataSource {

    val cached: HashMap<Int, Worker> = HashMap() // TODO

    override fun getWorkers(page: Int): List<WorkerData> {
        return remoteDataSource.getWorkers(page)
    }

    override fun getWorker(id: Int): WorkerDetailData {
        return remoteDataSource.getWorker(id)
    }

}