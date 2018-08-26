package com.wonka.staff.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Remote server endpoints.
 */
interface WonkaApi {

    @GET("/napptilus/oompa-loompas")
    fun getWorkersSync(@Query("page") page: Int = 1): Call<GetWorkersResponse>

    @GET("/napptilus/oompa-loompas/{id}")
    fun getWorker(@Path("id") id: Int): Call<WorkerDetailData>

}
