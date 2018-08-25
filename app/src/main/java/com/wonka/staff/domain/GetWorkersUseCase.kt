package com.wonka.staff.domain

import com.wonka.staff.data.WorkerRepository
import com.wonka.staff.data.remote.WorkerEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetWorkersUseCase @Inject constructor(
        private val repository: WorkerRepository
) : BaseUseCase<GetWorkersUseCase.Params, GetWorkersUseCase.Results>() {

    override fun execute(params: Params): Single<Results> {
        return repository.getWorkers()
                .map { t: List<WorkerEntity> -> Results(t) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    data class Params(val page: Int)
    data class Results(val workers: List<WorkerEntity>) // TODO create domain model classes

}

