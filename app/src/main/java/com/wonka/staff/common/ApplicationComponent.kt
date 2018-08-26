package com.wonka.staff.common

import android.app.Application
import com.wonka.staff.MyApplication
import com.wonka.staff.data.di.NetworkingModule
import com.wonka.staff.domain.di.DomainModule
import com.wonka.staff.ui.di.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (NetworkingModule::class),
    (AndroidInjectionModule::class),
    (ActivityBindingModule::class),
    (DomainModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(myApplication: MyApplication)

}