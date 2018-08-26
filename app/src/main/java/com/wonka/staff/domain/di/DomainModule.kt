package com.wonka.staff.domain.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Module responsible to provide domain layer dependencies.
 */
@Module
class DomainModule {

    @Provides
    @WorkerScheduler
    fun provideWorkerScheduler(): Scheduler = Schedulers.io()

    @Provides
    @DeliveryScheduler
    fun provideDeliveryScheduler(): Scheduler = AndroidSchedulers.mainThread()

}