package com.wonka.staff.workers

import com.wonka.staff.GetWorkersUseCase
import com.wonka.staff.common.BasePresenter
import javax.inject.Inject

class WorkersPresenter @Inject constructor(
        private val userCase: GetWorkersUseCase
) : BasePresenter<WorkersContract.View>(),
        WorkersContract.Presenter {

    override fun loadWorkers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        mView?.render(WorkersViewState())
    }

}

class WorkersViewState
