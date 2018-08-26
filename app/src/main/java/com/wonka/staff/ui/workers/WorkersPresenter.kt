package com.wonka.staff.ui.workers

import android.util.Log
import com.wonka.staff.domain.GetWorkersUseCase
import com.wonka.staff.ui.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScope
class WorkersPresenter @Inject constructor(
        private val userCase: GetWorkersUseCase
) : WorkersContract.Presenter {

    override var mView: WorkersContract.View? = null
    override val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mPaginationState: PaginationState

    private lateinit var mViewState: WorkersViewState

    init {
        mPaginationState = PaginationState(1)
    }

    override fun loadWorkers() {
        add(userCase.execute(GetWorkersUseCase.Params(mPaginationState.currentPage++))
                .doOnSubscribe {
                    mViewState = WorkersViewState.Loading(true)
                    renderState()
                }
                .doAfterTerminate { renderState() }
                .subscribe({ result ->
                    mViewState = WorkersViewState.Results(result.workers)
                }, { error ->
                    mViewState = WorkersViewState.Error(error)
                    Log.e("WorkerPresenter", "Error getting workers list", error)
                }))
    }

    private fun renderState() {
        mView?.renderViewSate(mViewState)
    }


    /**
     * Save current state of paginated call.
     */
    inner class PaginationState(var currentPage: Int)
}
