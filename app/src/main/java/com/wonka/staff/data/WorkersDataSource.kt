package com.wonka.staff.data

import com.wonka.staff.data.remote.WorkerData
import com.wonka.staff.data.remote.WorkerDetailData
import io.reactivex.Single

interface WorkersDataSource {

    fun getWorkers(): Single<List<WorkerData>>

    fun getWorker(id: Int): Single<WorkerDetailData>

}