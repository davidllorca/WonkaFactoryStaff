package com.wonka.staff

import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WorkerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : WorkersDataSource {

    val cache: Map<Int, WorkerEntity> = HashMap()

    override fun getWorkers(): Single<List<WorkerEntity>> {
        return remoteDataSource.getWorkers()
    }

    override fun getWorker(id: Int): Single<WorkerEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}