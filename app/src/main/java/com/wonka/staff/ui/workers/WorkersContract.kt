package com.wonka.staff.ui.workers

import com.wonka.staff.ui.base.BaseContract

interface WorkersContract {

    interface View : BaseContract.View<WorkersViewState>

    interface Presenter : BaseContract.Presenter<View> {

        fun loadWorkers()

    }

}