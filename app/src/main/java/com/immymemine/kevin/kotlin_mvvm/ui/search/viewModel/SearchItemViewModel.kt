package com.immymemine.kevin.kotlin_mvvm.ui.search.viewModel

import android.databinding.BaseObservable
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import com.immymemine.kevin.kotlin_mvvm.ui.ViewModel

/**
 * Created by quf93 on 2018-01-24.
 */
class SearchItemViewModel(searchItem : SearchItem) : BaseObservable(), ViewModel {

    @BindingAdapter({"bind:imageUrl"})
    fun loadImage(imageView : ImageView, imageUrl : String) {

    }

    override fun destroy() {

    }
}