package com.wonka.staff.ui.workerdetail

import com.wonka.staff.ui.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class WorkerDetailModule {

    @ActivityScope
    @Binds
    abstract fun bindWorkerDetailPresenter(presenter: WorkerDetailPresenter): WorkerDetailContract.Presenter

}