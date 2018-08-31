package com.wonka.staff.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: WonkaApi) {

    companion object {
        private const val DEFAULT_CURRENT_PAGE: Int = 1
        private const val DEFAULT_TOTAL_PAGE: Int = 1
    }

    var currentPage = DEFAULT_CURRENT_PAGE
    var totalPage = DEFAULT_TOTAL_PAGE

    fun getWorkers(page: Int): List<WorkerData> {
        if (page <= totalPage) {
            val call = api.getWorkersSync(page)

            val response = call.execute()

            if (response.isSuccessful)
                with(response.body()) {
                    currentPage = this?.current ?: DEFAULT_CURRENT_PAGE
                    totalPage = this?.total ?: DEFAULT_TOTAL_PAGE

                    return this?.results  ?: listOf()
                }
            else {
                throw Exception(response.message())
            }
        }
        return listOf()
    }

    fun getWorker(id: Int): WorkerDetailData {
        val call = api.getWorker(id)

        val response = call.execute()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception(response.message())
        }
    }

}