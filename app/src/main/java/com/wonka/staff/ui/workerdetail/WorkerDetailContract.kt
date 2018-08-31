package com.wonka.staff.ui.workerdetail

import com.wonka.staff.ui.base.BaseContract

/**
 * Contract between the View and the Presenter.
 */
interface WorkerDetailContract {

    interface View : BaseContract.View<WorkerDetailViewState>

    interface Presenter : BaseContract.Presenter<View> {

        fun loadWorkerDetail(id: Int)

    }

    interface WorkerDetailViewObject {
        val name: String
        val gender: String
        val profession: String
        val avatarUrl: String
        val description: String
        val quota: String
        val height: Int
        val country: String
        val age: Int
        val favorite: FavoriteViewObject
        val email: String

        interface FavoriteViewObject {
            val color: String
            val food: String
            val randomString: String
            val song: String
        }
    }

}