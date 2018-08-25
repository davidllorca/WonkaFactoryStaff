package com.wonka.staff.ui.base

import io.reactivex.disposables.Disposable

class BaseContract {

    interface Presenter<in View> {
        fun add(vararg disposable: Disposable)
        fun dispose()
        fun attach(view: View)
        fun dropView()
    }

    interface View<in ViewState> {
        fun render(state: ViewState)
    }

}