package com.wonka.staff

import com.wonka.staff.common.WonkaApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: WonkaApi) : WorkersDataSource {

    override fun getWorkers(): List<WorkerEntity> {
        return api.getWorkers().execute().body()?.results!! // TODO review nullabilities, just left to compile
    }

    override fun getWorker(id: Int): WorkerEntity {
        return api.getWorker(id).execute().body()!! // TODO review nullabilities, just left to compile
    }

}