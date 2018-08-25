package com.wonka.staff.common

import android.app.Application
import com.wonka.staff.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (NetworkingModule::class),
    (WonkaApiModule::class),
    (AndroidInjectionModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(myApplication: MyApplication)

}