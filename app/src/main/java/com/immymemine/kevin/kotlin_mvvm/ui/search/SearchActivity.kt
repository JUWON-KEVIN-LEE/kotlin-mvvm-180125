package com.immymemine.kevin.kotlin_mvvm.ui.search

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
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
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SearchItemAdapter()
    }

    override fun onSearchItemsChanged(searchItems: List<SearchItem>) {
        Log.d("JUWONLEE", "items changed")
        val adapter = recyclerView.adapter as SearchItemAdapter
        adapter.setSearchItems(searchItems)
        adapter.notifyDataSetChanged()

        hideSoftKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.destroy()
    }

    private fun hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.textSearch.windowToken, 0)
    }
}
