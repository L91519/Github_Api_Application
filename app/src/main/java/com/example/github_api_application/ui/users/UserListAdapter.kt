package com.example.github_api_application.ui.users

import android.view.ViewGroup
import com.example.base.ui.BaseViewModel
import com.example.base.ui.recyclerView.BaseRecyclerViewAdapter
import com.example.base.ui.recyclerView.BaseViewHolder
import com.example.github_api_application.R
import com.example.github_api_application.model.vo.User

class UserListAdapter : BaseRecyclerViewAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return UserListViewHolder(parent)
    }

    override fun getItemLayoutRes() = R.layout.item_user

    private inner class UserListViewHolder(parent: ViewGroup) : BaseViewHolder(getItemLayoutRes(), parent) {
        override fun bind(item: Any?, viewModel: BaseViewModel?) {
            super.bind(item, viewModel)

            binding.root.setOnClickListener {
                (viewModel as UsersViewModel).onClickUser(item as User)
            }
        }
    }
}