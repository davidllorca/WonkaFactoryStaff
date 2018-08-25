package com.wonka.staff.workers

import android.util.Log
import com.wonka.staff.GetWorkersUseCase
import com.wonka.staff.common.ActivityScope
import com.wonka.staff.common.BasePresenter
import javax.inject.Inject

@ActivityScope
class WorkersPresenter @Inject constructor(
        private val userCase: GetWorkersUseCase
) : BasePresenter<WorkersContract.View>(),
        WorkersContract.Presenter {

    override fun loadWorkers() {
        add(userCase.execute(GetWorkersUseCase.Params(1))
                .subscribe({ t1, t2 -> Log.d("Workers", "$t1 /// ${t1.workers}") }))
        mView?.render(WorkersViewState())
    }

}

class WorkersViewState
