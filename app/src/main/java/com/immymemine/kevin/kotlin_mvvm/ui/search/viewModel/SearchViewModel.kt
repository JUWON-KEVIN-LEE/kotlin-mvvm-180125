package com.immymemine.kevin.kotlin_mvvm.ui.search.viewModel

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.immymemine.kevin.kotlin_mvvm.data.Injector
import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import com.immymemine.kevin.kotlin_mvvm.ui.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by quf93 on 2018-01-24.
 */
class SearchViewModel(var dataListener: DataListener?) : ViewModel{

    var searchButtonVisibility : ObservableInt
    var textInfoVisibility : ObservableInt
    var progressVisibility : ObservableInt
    var recyclerViewVisibility : ObservableInt

    var textInfo : ObservableField<String>

    var disposable : DisposableObserver<List<SearchItem>>? = null
    var searchItems : List<SearchItem>? = null

    lateinit var editTextSearchContent : String

    init {
        searchButtonVisibility = ObservableInt(View.GONE)
        textInfoVisibility = ObservableInt(View.INVISIBLE)
        progressVisibility = ObservableInt(View.INVISIBLE)
        recyclerViewVisibility = ObservableInt(View.INVISIBLE)

        textInfo = ObservableField("Search for ")
    }

    fun clickSearchButton(view : View) {
        loadClasses(editTextSearchContent)
    }

    fun getSearchEditTextWatcher() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editTextSearchContent = s.toString()
                if(s?.length!! > 0) searchButtonVisibility = ObservableInt(View.VISIBLE) else searchButtonVisibility = ObservableInt(View.GONE)
            }
        }
    }

    private fun loadClasses(searchContent : String) {
        textInfoVisibility = ObservableInt(View.INVISIBLE)
        progressVisibility.set(View.VISIBLE)
        recyclerViewVisibility = ObservableInt(View.INVISIBLE)

        val injector = Injector()
        val api = injector.provideApi()

        disposable = api.search("TEST ID")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(OperationObserver())
    }

    interface DataListener {
        fun onSearchItemsChanged(searchItems : List<SearchItem>)
    }

    private inner class OperationObserver : DisposableObserver<List<SearchItem>>() {
        override fun onNext(value: List<SearchItem>?) {
            this@SearchViewModel.searchItems = value
        }

        // test
        override fun onError(e: Throwable?) {
            var searchItem = SearchItem()
            searchItem._id = "TEST ID"
            searchItem.imageUrl = "https://avatars0.githubusercontent.com/u/2928633?s=88&v=4"
            searchItem.title = "TITLE : HELLO MVVM WITH KOTLIN TEST"
            searchItem.tutorName = "JUWON KEVIN LEE"
            var searchItems = ArrayList<SearchItem>()
            searchItems.add(searchItem)

            dataListener?.onSearchItemsChanged(searchItems)

            progressVisibility.set(View.INVISIBLE)
            recyclerViewVisibility.set(View.VISIBLE)
        }

        override fun onComplete() {
            dataListener?.onSearchItemsChanged(searchItems!!)
            progressVisibility.set(View.INVISIBLE)

            if(!searchItems?.isEmpty()!!) {
                recyclerViewVisibility.set(View.VISIBLE)
            } else {
                textInfo.set("No search Result.")
                textInfoVisibility.set(View.VISIBLE)
            }
        }
    }

    override fun destroy() {
        if(!disposable?.isDisposed!!) disposable?.dispose()
        dataListener = null
    }
}