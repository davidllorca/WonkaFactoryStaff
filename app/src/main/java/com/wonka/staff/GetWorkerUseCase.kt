package com.wonka.staff

class GetWorkerUseCase(
        private val repository: WorkerRepository
) : BaseUseCase<GetWorkerUseCase.Params, GetWorkerUseCase.Results>() {

    override fun execute(params: Params): Results {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class Params(val id: Int)
    data class Results(val worker: WorkerEntity) // TODO create domain model class
}

