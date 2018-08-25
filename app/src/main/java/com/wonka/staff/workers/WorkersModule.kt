package com.wonka.staff.workers

import com.wonka.staff.common.ActivityScope
import dagger.Binds
import dagger.Module

@Module
abstract class WorkersModule {

    @ActivityScope
    @Binds
    abstract fun bindWorkersPresenter(presenter: WorkersPresenter): WorkersContract.Presenter

}