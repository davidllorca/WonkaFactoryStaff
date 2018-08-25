package com.wonka.staff

interface WorkersDataSource {

    fun getWorkers(): List<WorkerEntity>

    fun getWorker(id: Int): WorkerEntity

}