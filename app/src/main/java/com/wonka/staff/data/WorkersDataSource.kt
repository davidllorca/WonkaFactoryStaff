package com.wonka.staff.data

import com.wonka.staff.data.remote.WorkerData
import com.wonka.staff.data.remote.WorkerDetailData

interface WorkersDataSource {

    fun getWorkers(page: Int): List<WorkerData>

    fun getWorker(id: Int): WorkerDetailData

}