package com.wonka.staff

import android.app.Activity
import android.app.Application
import com.wonka.staff.common.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector


class MyApplication : Application(), HasActivityInjector {

    private lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = mActivityInjector
}