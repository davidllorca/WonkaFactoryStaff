package com.wonka.staff.ui.workers

import com.wonka.staff.ui.base.BaseContract

/**
 * Contract between the View and the Presenter.
 */
interface WorkersContract {

    interface View : BaseContract.View<WorkersViewState>

    interface Presenter : BaseContract.Presenter<View> {

        fun loadWorkers()

    }

}