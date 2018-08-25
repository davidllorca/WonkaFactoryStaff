package com.wonka.staff.common

import com.wonka.staff.workers.WorkersActivity
import com.wonka.staff.workers.WorkersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(WorkersModule::class)])
    abstract fun workersActivity(): WorkersActivity
}
