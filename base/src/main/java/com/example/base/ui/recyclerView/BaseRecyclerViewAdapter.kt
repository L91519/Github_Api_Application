package com.example.base.ui.recyclerView

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.base.ui.BaseViewModel

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    @LayoutRes
    abstract fun getItemLayoutRes(): Int

    open var viewModel: BaseViewModel? = null
    var itemList = listOf<Any>()

    open fun updateList(items: List<Any>, diffCallback: (Any) -> Any?) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(itemList, items, diffCallback))
        itemList = items
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(getItemLayoutRes(), parent)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = holder.bind(itemList[position], viewModel)

    override fun getItemCount() = itemList.size

//    override fun getItemViewType(position: Int) = getItemLayoutRes()

}