package com.wonka.staff.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class BaseContract {

    interface Presenter<View> {

        var mView: View?
        val mCompositeDisposable: CompositeDisposable

        /**
         * Adds [disposables] to container [mCompositeDisposable]
         */
        fun add(vararg disposables: Disposable) {
            mCompositeDisposable.addAll(*disposables)
        }

        /**
         * Release all [Disposable] in [mCompositeDisposable]
         */
        fun dispose() {
            mCompositeDisposable.clear()
        }

        /**
         * Binds presenter with a view when resumed.
         *
         * @param view the view associated with this presenter
         */
        fun attach(view: View) {
            mView = view
        }

        /**
         * Drops the reference to the view when destroyed.
         */
        fun dropView() {
            mView = null
            dispose()
        }
    }

    interface View<in ViewState> {
        fun render(state: ViewState)
    }

}