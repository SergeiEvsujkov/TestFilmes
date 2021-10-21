package com.coffeemolkin.testfilmes.images

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.coffeemolkin.testfilmes.R

class GlideImageLoader  : IImageLoader<ImageView> {

    override fun loadTo(url: String, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .error(R.drawable.no_image)
            .into(target)

    }
}