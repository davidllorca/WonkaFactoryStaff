package com.wonka.staff.ui.base

import android.widget.ImageView
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageLoader @Inject constructor(private val picasso: Picasso) {

    fun loadImage(uri: String, target: ImageView) {
        picasso
                .load(uri)
                .into(target)
    }

}