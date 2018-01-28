package com.immymemine.kevin.kotlin_mvvm

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

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
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    Log.d("JUWONLEE", e!!.message)
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    Log.d("JUWONLEE", "Ready")
                    return false
                }
            })
            .into(imageView)
}