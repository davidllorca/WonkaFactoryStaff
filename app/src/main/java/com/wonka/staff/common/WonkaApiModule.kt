package com.wonka.staff.common

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Module responsible to provide remote services.
 */
@Module
class WonkaApiModule {

    private val WONKA_API_BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com"

    @Singleton
    @Provides
    fun provideWonkaApiModule(retrofit: Retrofit): WonkaApi =
            retrofit.newBuilder()
                    .baseUrl(WONKA_API_BASE_URL)
                    .build()
                    .create<WonkaApi>(WonkaApi::class.java)

}