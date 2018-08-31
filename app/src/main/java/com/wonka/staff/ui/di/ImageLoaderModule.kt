package com.wonka.staff.ui.di

import android.content.Context
import com.squareup.picasso.BuildConfig
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.wonka.staff.ui.base.ImageLoader
import dagger.Module
import dagger.Provides


@Module
class ImageLoaderModule {

    @Provides
    fun providePicasso(context: Context): Picasso {
        val builder = Picasso.Builder(context)
        builder.downloader(OkHttp3Downloader(context, 15 * 1024 * 1024))
        val built = builder.build()
        built.setIndicatorsEnabled(BuildConfig.DEBUG)
        built.isLoggingEnabled = true
        return built
    }

    @Provides
    fun provideImageLoader(picasso: Picasso): ImageLoader {
        return ImageLoader(picasso)
    }

}