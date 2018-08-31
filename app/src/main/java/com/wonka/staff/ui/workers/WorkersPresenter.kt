package com.wonka.staff.ui.workers

import android.util.Log
import com.wonka.staff.domain.GetWorkersUseCase
import com.wonka.staff.ui.di.ActivityScope
import com.wonka.staff.ui.model.toWorkersView
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
        // First state at instance creation time.
        mPaginationState = PaginationState(0)
    }

    override fun loadWorkers() {
        add(userCase.execute(GetWorkersUseCase.Params(mPaginationState.currentPage++))
                .doOnSubscribe {
                    mViewState = WorkersViewState.Loading()
                    renderState()
                }
                .doAfterTerminate { renderState() }
                .subscribe({ result ->
                    mViewState = WorkersViewState.Results(result.workers.toWorkersView())
                }, { error ->
                    mViewState = WorkersViewState.Error(error)
                    Log.e("WorkersPresenter", "Error getting workers list", error)
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
