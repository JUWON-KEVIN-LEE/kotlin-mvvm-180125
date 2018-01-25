package com.immymemine.kevin.kotlin_mvvm.ui.search

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.immymemine.kevin.kotlin_mvvm.R
import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import com.immymemine.kevin.kotlin_mvvm.databinding.ActivitySearchBinding
import com.immymemine.kevin.kotlin_mvvm.ui.search.viewModel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(), SearchViewModel.DataListener {

    lateinit var binding: ActivitySearchBinding
    lateinit var searchViewModel : SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        searchViewModel = SearchViewModel(this)
        binding.viewModel = searchViewModel

        initiateView()
    }

    private fun initiateView() {
        recycler_view.layoutManager = LinearLayoutManager(this)

    }

    override fun onSearchItemsChanged(searchItems: List<SearchItem>) {


    }
}
