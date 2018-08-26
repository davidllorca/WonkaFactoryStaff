package com.wonka.staff.ui.di

import android.content.Context
import com.squareup.picasso.Picasso
import com.wonka.staff.ui.base.ImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageLoaderModule {

    @Provides
    fun providePicasso(context: Context): Picasso {
        return Picasso.Builder(context)
                .build()
    }

    @Provides
    fun provideImageLoader(): ImageLoader {
        return ImageLoader()
    }

}