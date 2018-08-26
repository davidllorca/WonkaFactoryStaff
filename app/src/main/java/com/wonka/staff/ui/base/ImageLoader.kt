package com.wonka.staff.ui.base

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {

    fun loadImage(uri: String, target: ImageView) {
        Picasso.get() // TODO CUSTOMIZE PICASSO AS GLOBAL
                .load(uri)
                .into(target)
    }

}