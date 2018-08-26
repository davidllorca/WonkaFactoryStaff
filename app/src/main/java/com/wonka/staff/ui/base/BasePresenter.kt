package com.wonka.staff.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<View> : BaseContract.Presenter<View> {

    protected var mView: View? = null
    private val mDisposables: CompositeDisposable = CompositeDisposable()

    override fun add(vararg disposable: Disposable) {
        mDisposables.addAll(*disposable)
    }

    final override fun dispose() {
        mDisposables.dispose()
    }

    override fun attach(view: View) {
        mView = view
    }

    override fun dropView() {
        mView = null
        dispose()
    }

}