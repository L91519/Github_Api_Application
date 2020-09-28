package com.example.github_api_application.base.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.github_api_application.BR
import com.example.github_api_application.base.BaseViewModel

open class BaseViewHolder(@LayoutRes layoutRes: Int, parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)) {

    val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    open fun bind(item: Any?, viewModel: BaseViewModel? = null) {
        binding.run {
            setVariable(BR.viewModel, viewModel)
            setVariable(BR.item, item)
        }
    }
}