package com.wonka.staff.domain

import com.wonka.staff.data.WorkerRepository
import com.wonka.staff.domain.di.DeliveryScheduler
import com.wonka.staff.domain.di.WorkerScheduler
import com.wonka.staff.domain.model.Worker
import com.wonka.staff.domain.model.toWorkers
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetWorkersUseCase @Inject constructor(
        private val repository: WorkerRepository,
        @WorkerScheduler private val workerThread: Scheduler,
        @DeliveryScheduler private val deliveryScheduler: Scheduler
) : BaseUseCase<GetWorkersUseCase.Params, GetWorkersUseCase.Results>() {

    override fun execute(params: Params): Single<Results> {
        return repository.getWorkers()
                .map { workersData -> Results(workersData.toWorkers()) }
                .subscribeOn(workerThread)
                .observeOn(deliveryScheduler)
    }

    data class Params(val page: Int)
    data class Results(val workers: List<Worker>) // TODO create domain model classes

}

