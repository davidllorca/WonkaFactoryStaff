package com.wonka.staff.common

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (ApplicationModule::class),
    (NetworkingModule::class),
    (WonkaApiModule::class)])
interface ApplicationComponent