package com.wonka.staff.domain

import com.wonka.staff.data.WorkerRepository
import com.wonka.staff.data.remote.FavoriteData
import com.wonka.staff.data.remote.WorkerDetailData
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetWorkerDetailUseCaseTest {


    private val workerScheduler: Scheduler = Schedulers.trampoline()
    private val deliveryScheduler: Scheduler = Schedulers.trampoline()

    @Mock
    private lateinit var repository: WorkerRepository

    private var workersTestObserver: TestObserver<GetWorkerDetailUseCase.Results> = TestObserver()


    private val ID_WORKER: Int = 1

    private val params: GetWorkerDetailUseCase.Params = GetWorkerDetailUseCase.Params(ID_WORKER)
    private val workerDetailData = WorkerDetailData(
            "LastName",
            "Description",
            "https://url",
            "Profession",
            "Quota",
            100,
            "FirstName",
            "Country",
            22,
            FavoriteData("Color", "Food", "Random", "Song"),
            "M",
            "email@wonka.com")

    private lateinit var useCase: GetWorkerDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        useCase = GetWorkerDetailUseCase(repository, workerScheduler, deliveryScheduler)
    }

    @Test
    fun `when use case is executed then worker detail is returned`() {
        Mockito.`when`(repository.getWorker(ID_WORKER)).thenReturn(workerDetailData)

        useCase.execute(params).subscribe(workersTestObserver)

        val results = workersTestObserver.values()[0]
        assert(workerDetailData.firstName == results.worker.firstName)
        assert(workerDetailData.lastName == results.worker.lastName)
        assert(workerDetailData.quota == results.worker.quota)
        assert(workerDetailData.description == results.worker.description)
        assert(workerDetailData.image == results.worker.image)
        assert(workerDetailData.height == results.worker.height)
        assert(workerDetailData.country == results.worker.country)
        assert(workerDetailData.age == results.worker.age)
        assert(workerDetailData.gender == results.worker.gender)
        assert(workerDetailData.email == results.worker.email)
    }

}
