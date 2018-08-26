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

    private var paginationState: PaginationState

    init {
        paginationState = PaginationState(1, null)
    }

    override fun loadWorkers() {
        add(userCase.execute(GetWorkersUseCase.Params(paginationState.currentPage++))
                .doOnSubscribe { mView?.renderViewSate(WorkersViewState.Loading(true)) }
                .subscribe({ result -> mView?.renderViewSate(WorkersViewState.Results(result.workers)) },
                        { error ->
                            mView?.renderViewSate(WorkersViewState.Error(error))
                            Log.e("WorkerPresenter", "Error getting workers list", error)
                        }))
    }

    inner class PaginationState(var currentPage: Int, val totalPage: Int?)
}
