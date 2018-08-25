package com.wonka.staff

import android.app.Application
import com.wonka.staff.common.ApplicationComponent
import com.wonka.staff.common.ApplicationModule
import com.wonka.staff.common.DaggerApplicationComponent

class MyApplication : Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent = mApplicationComponent

}