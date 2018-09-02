package com.wonka.staff.domain

import com.wonka.staff.data.WorkerRepository
import com.wonka.staff.data.remote.FavoriteData
import com.wonka.staff.data.remote.WorkerData
import io.reactivex.Scheduler
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetWorkersUseCaseTest {

    private val workerScheduler: Scheduler = Schedulers.trampoline()
    private val deliveryScheduler: Scheduler = Schedulers.trampoline()

    @Mock
    private lateinit var repository: WorkerRepository

    private var workersTestObserver: TestObserver<GetWorkersUseCase.Results> = TestObserver()


    private val N_PAGE: Int = 0

    private val params: GetWorkersUseCase.Params = GetWorkersUseCase.Params(N_PAGE)
    private val workerData = WorkerData("Name",
            "LastName",
            FavoriteData("Color", "Food", "Random", "Song"),
            "M",
            "https://url",
            "Profession",
            "email@wonka.com",
            22,
            "Country",
            100,
            1)
    private val list = listOf(workerData, workerData.copy(id = 2), workerData.copy(id = 3))


    private lateinit var useCase: GetWorkersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        useCase = GetWorkersUseCase(repository, workerScheduler, deliveryScheduler)
    }

    @Test
    fun `when use case is executed then worker list is returned`() {
        Mockito.`when`(repository.getWorkers(N_PAGE)).thenReturn(list)

        useCase.execute(params).subscribe(workersTestObserver)

        val results = workersTestObserver.values()[0]
        assert(results.workers.size == list.size)
    }

}
