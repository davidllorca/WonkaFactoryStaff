package com.wonka.staff.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Remote server endpoints.
 */
interface WonkaApi {

    @GET("napptilus/oompa-loompas")
    fun getWorkers(@Query("page") page: Int = 1): Single<GetWorkersResponse>

    @GET("napptilus/oompa-loompas/{id}")
    fun getWorker(@Path("id") id: Int): Single<WorkerEntity>

}
