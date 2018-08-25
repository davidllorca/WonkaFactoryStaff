package com.wonka.staff.ui.workers

import com.wonka.staff.ui.di.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class WorkersModule {

    @ActivityScope
    @Binds
    abstract fun bindWorkersPresenter(presenter: WorkersPresenter): WorkersContract.Presenter

}