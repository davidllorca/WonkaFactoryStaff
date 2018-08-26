package com.wonka.staff.domain

import com.wonka.staff.data.WorkerRepository
import com.wonka.staff.domain.di.DeliveryScheduler
import com.wonka.staff.domain.di.WorkerScheduler
import com.wonka.staff.domain.model.WorkerDetail
import com.wonka.staff.domain.model.toWorkerDetail
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetWorkerDetailUseCase @Inject constructor(
        private val repository: WorkerRepository,
        @WorkerScheduler private val workerThread: Scheduler,
        @DeliveryScheduler private val deliveryScheduler: Scheduler
) : BaseUseCase<GetWorkerDetailUseCase.Params, GetWorkerDetailUseCase.Results>() {

    override fun execute(params: Params): Single<Results> {
        return Single.fromCallable({ repository.getWorker(params.id) })
                .map { workerDetailData -> Results(workerDetailData.toWorkerDetail()) }
                .subscribeOn(workerThread)
                .observeOn(deliveryScheduler)
    }

    data class Params(val id: Int)
    data class Results(val worker: WorkerDetail)
}

