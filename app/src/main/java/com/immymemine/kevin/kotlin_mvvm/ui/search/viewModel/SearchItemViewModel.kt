package com.immymemine.kevin.kotlin_mvvm.ui.search.viewModel

import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import com.immymemine.kevin.kotlin_mvvm.ui.ViewModel

/**
 * Created by quf93 on 2018-01-24.
 */
class SearchItemViewModel(var searchItem : SearchItem) : BaseObservable(), ViewModel {

    fun getImageUrl() : String? {
        Log.d("JUWONLEE", "image url")
        return searchItem.imageUrl
    }

    fun getTitle() : String? {
        Log.d("JUWONLEE", "title")
        return searchItem.title
    }

    fun getTutorName() : String? {
        Log.d("JUWONLEE", "name")
        return searchItem.tutorName
    }

    fun onClick(view : View) {
        Toast.makeText(view.context, "This item's id is " + searchItem._id, Toast.LENGTH_LONG).show()
    }

    override fun destroy() {

    }
}