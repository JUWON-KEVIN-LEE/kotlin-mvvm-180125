package com.immymemine.kevin.kotlin_mvvm.ui.search

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.immymemine.kevin.kotlin_mvvm.R
import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import com.immymemine.kevin.kotlin_mvvm.databinding.ItemSearchBinding
import com.immymemine.kevin.kotlin_mvvm.ui.search.viewModel.SearchItemViewModel

/**
 * Created by quf93 on 2018-01-24.
 */
class SearchItemAdapter : RecyclerView.Adapter<SearchItemAdapter.ViewHolder>() {

    lateinit var searchItems : List<SearchItem>

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemSearchBinding>(LayoutInflater.from(parent?.context), R.layout.item_search, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    }

    class ViewHolder(var binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.viewHolder) {
        internal fun bindSearchItem(searchItem : SearchItem) {
            if(binding.viewModel == null) {
                binding.viewModel = SearchItemViewModel(searchItem)
            } else {

            }
        }
    }
}