package com.wonka.staff.ui.di

import com.wonka.staff.ui.workers.WorkersActivity
import com.wonka.staff.ui.workers.WorkersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(WorkersModule::class)])
    abstract fun workersActivity(): WorkersActivity
}
