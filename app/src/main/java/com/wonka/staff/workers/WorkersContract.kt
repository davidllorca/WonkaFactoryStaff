package com.wonka.staff.workers

import com.wonka.staff.common.BaseContract

interface WorkersContract {

    interface View : BaseContract.View<WorkersViewState>

    interface Presenter {

        fun loadWorkers()

    }

}