package com.sun.efood.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sun.efood.R

fun ImageView.loadImage(image: String) {
    Glide.with(context)
        .load(image)
        .error(R.drawable.ic_error)
        .placeholder(R.drawable.ic_error)
        .into(this)
}
