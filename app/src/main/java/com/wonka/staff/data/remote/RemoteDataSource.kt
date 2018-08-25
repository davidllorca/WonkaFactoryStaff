package com.wonka.staff.data.remote

import com.wonka.staff.data.WorkersDataSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: WonkaApi) : WorkersDataSource {

    override fun getWorkers(): Single<List<WorkerEntity>> {
        return api.getWorkers().map { response -> response.results }
    }

    override fun getWorker(id: Int): Single<WorkerEntity> {
        return api.getWorker(id)
    }

}