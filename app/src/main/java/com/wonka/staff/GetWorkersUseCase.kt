package com.wonka.staff

class GetWorkersUseCase(
        private val repository: WorkerRepository
) : BaseUseCase<GetWorkersUseCase.Params, GetWorkersUseCase.Results>() {

    override fun execute(params: Params): Results {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class Params(val page: Int)
    data class Results(val workers: List<WorkerEntity>) // TODO create domain model classes

}

