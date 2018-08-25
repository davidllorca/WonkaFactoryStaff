package com.wonka.staff

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : WorkersDataSource {

    val cache: Map<Int, WorkerEntity> = HashMap()

    override fun getWorkers(): List<WorkerEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWorker(id: Int): WorkerEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}