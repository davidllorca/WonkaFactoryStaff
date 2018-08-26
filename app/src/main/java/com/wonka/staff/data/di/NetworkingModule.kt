package com.wonka.staff.data.di

import com.wonka.staff.BuildConfig
import com.wonka.staff.data.remote.WonkaApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module responsible to provide HTTP client implementation.
 */
@Module
class NetworkingModule {

    private val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // TODO set own threading config
            .build()


    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideWonkaApi(retrofit: Retrofit): WonkaApi =
            retrofit.create<WonkaApi>(WonkaApi::class.java)
}