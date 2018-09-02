package com.wonka.staff.ui.workerdetail

import com.nhaarman.mockitokotlin2.capture
import com.wonka.staff.domain.GetWorkerDetailUseCase
import com.wonka.staff.domain.model.Favorite
import com.wonka.staff.domain.model.WorkerDetail
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WorkerDetailPresenterTest {

    @Mock
    private lateinit var view: WorkerDetailContract.View

    @Mock
    private lateinit var useCase: GetWorkerDetailUseCase

    private val params: GetWorkerDetailUseCase.Params = GetWorkerDetailUseCase.Params(1)

    private val workerDetail: WorkerDetail = WorkerDetail("Name",
            "LastName",
            "Description",
            "Quota",
            "http://avatarUrl",
            "Profession",
            100,
            "Country",
            22,
            Favorite("Color", "Food", "Random", "Song"),
            "F",
            "email"
    )

    private val results: GetWorkerDetailUseCase.Results = GetWorkerDetailUseCase.Results(workerDetail)

    private lateinit var presenter: WorkerDetailPresenter

    @Captor
    private var captor: ArgumentCaptor<WorkerDetailViewState> = ArgumentCaptor.forClass(WorkerDetailViewState::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = WorkerDetailPresenter(useCase)
        presenter.attach(view)
    }

    @Test
    fun `when load worker detail then view render Loading and Results state`() {
        setUpSuccess()

        presenter.loadWorkerDetail(1)

        Mockito.verify(view, Mockito.times(2)).renderViewSate(capture(captor))

        // Verify that view render 2 states
        assertThat("Loading state is running first", captor.allValues[0] is WorkerDetailViewState.Loading)
        assertThat("Results state is running next", captor.allValues[1] is WorkerDetailViewState.Results)
    }

    @Test
    fun `when error happens loading worker detail then view render Loading and Error state`() {
        setUpError()

        presenter.loadWorkerDetail(1)

        Mockito.verify(view, Mockito.times(2)).renderViewSate(capture(captor))

        // Verify that view render 2 states
        assertThat("Loading state should run first", captor.allValues[0] is WorkerDetailViewState.Loading)
        assertThat("Error state should run next Loading", captor.allValues[1] is WorkerDetailViewState.Error)
    }

    private fun setUpSuccess() {
        Mockito.`when`(useCase.execute(params)).thenReturn(Single.just(results))
    }

    private fun setUpError() {
        Mockito.`when`(useCase.execute(params)).thenReturn(Single.error(Throwable("Whatever")))
    }

}