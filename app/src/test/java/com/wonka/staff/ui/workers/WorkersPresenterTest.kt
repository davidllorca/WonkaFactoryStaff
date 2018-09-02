package com.wonka.staff.ui.workers

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.times
import com.wonka.staff.domain.GetWorkersUseCase
import com.wonka.staff.domain.model.Worker
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WorkersPresenterTest {

    @Mock
    private lateinit var view: WorkersContract.View

    @Mock
    private lateinit var useCase: GetWorkersUseCase

    private val params: GetWorkersUseCase.Params = GetWorkersUseCase.Params(0)

    private val worker = Worker(1,"Name", "M", "Profession", "https://url")
    private val list = listOf(worker, worker.copy(id = 2), worker.copy(id = 3))

    private val results: GetWorkersUseCase.Results = GetWorkersUseCase.Results(list)

    private lateinit var presenter: WorkersPresenter

    @Captor
    private var captor: ArgumentCaptor<WorkersViewState> = ArgumentCaptor.forClass(WorkersViewState::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = WorkersPresenter(useCase)
        presenter.attach(view)
    }

    @Test
    fun `when load workers then view render Loading and Results state`() {
        setUpSuccess()

        presenter.loadWorkers()

        Mockito.verify(view, Mockito.times(2)).renderViewSate(capture(captor))

        // Verify that view render 2 states
        assertThat("Loading state is running first", captor.allValues[0] is WorkersViewState.Loading)
        assertThat("Results state is running next", captor.allValues[1] is WorkersViewState.Results)
    }

    @Test
    fun `when error happens loading workers then view render Loading and Error state`() {
        setUpError()

        presenter.loadWorkers()

        Mockito.verify(view, Mockito.times(2)).renderViewSate(capture(captor))

        // Verify that view render 2 states
        assertThat("Loading state should run first", captor.allValues[0] is WorkersViewState.Loading)
        assertThat("Error state should run next Loading", captor.allValues[1] is WorkersViewState.Error)
    }

    @Test
    fun `when request new page then page argument is increasing`(){
        var captor: ArgumentCaptor<GetWorkersUseCase.Params> = ArgumentCaptor.forClass(GetWorkersUseCase.Params::class.java)
        Mockito.`when`(useCase.execute(capture(captor))).thenReturn(Single.just(results))

        presenter.loadWorkers()
        assertThat("First call should be page=0", captor.allValues[0].page == 0)

        presenter.loadWorkers()
        assertThat("First call should be page=1", captor.allValues[1].page == 1)

        presenter.loadWorkers()
        assertThat("First call should be page=2", captor.allValues[2].page == 2)
        Mockito.verify(useCase, times(3)).execute(capture(captor))

    }

    private fun setUpSuccess() {
        Mockito.`when`(useCase.execute(params)).thenReturn(Single.just(results))
    }

    private fun setUpError() {
        Mockito.`when`(useCase.execute(params)).thenReturn(Single.error(Throwable("Whatever")))
    }

}