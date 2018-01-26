package com.immymemine.kevin.kotlin_mvvm

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions

/**
 * Created by quf93 on 2018-01-26.
 */

@BindingAdapter("imageUrl")
fun loadImage(imageView : ImageView, imageUrl : String) {
    Log.d("JUWONLEE", "load image imageUrl : " + imageUrl + ", id : " +imageView.context)

    GlideApp.with(imageView)
//            .asGif()
            .load(imageUrl)
            .apply(RequestOptions.centerCropTransform())
            .into(imageView)
}