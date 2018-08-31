package com.wonka.staff.ui.workers

import com.nhaarman.mockitokotlin2.capture
import com.wonka.staff.domain.GetWorkersUseCase
import com.wonka.staff.domain.model.Worker
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
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

    var captor: ArgumentCaptor<WorkersViewState> = ArgumentCaptor.forClass(WorkersViewState::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = WorkersPresenter(useCase)
        presenter.attach(view)

        Mockito.`when`(useCase.execute(params)).thenReturn(Single.just(results))

    }

    @Test
    fun `when load workers then view render Loading and Results state`() {

        presenter.loadWorkers()

        Mockito.verify(view, Mockito.times(2)).renderViewSate(capture(captor))

        // Verify that view render 2 states
        assertThat("Loading state is running first", captor.allValues[0] is WorkersViewState.Loading)
        assertThat("Result state is running next", captor.allValues[1] is WorkersViewState.Results)
    }

    // TODO continue here 31/08/18


}