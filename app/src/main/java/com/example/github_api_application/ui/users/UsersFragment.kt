package com.example.github_api_application.ui.users

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.base.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentUsersBinding
import com.example.github_api_application.model.vo.UserType

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(
    R.layout.fragment_users, UsersViewModel::class.java) {

    private lateinit var userRecyclerViewAdapter: BaseRecyclerViewAdapter
    lateinit var userType: UserType

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userType = UsersFragmentArgs.fromBundle(arguments?:return).userType

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetch(userType)

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.toolbarContentLayout.titleTextView.text = when(userType) {
            UserType.FOLLOWER -> UserType.FOLLOWER.value
            UserType.FOLLOWING -> UserType.FOLLOWING.value
            UserType.STARGAZER -> UserType.STARGAZER.value
        }
        setupRecyclerView()
    }

    private fun subscribeUI() {
        viewModel.userList.observe(viewLifecycleOwner, Observer {
            userRecyclerViewAdapter.updateList(it) { { it } }
        })

        viewModel.navigateToBack.observe(viewLifecycleOwner, Observer {
            activity?.onBackPressed()
        })

    }

    private fun setupRecyclerView() {
        userRecyclerViewAdapter = object : BaseRecyclerViewAdapter() {
            override fun getItemLayoutRes(): Int = R.layout.item_user
        }
        binding.userRecyclerView.adapter = userRecyclerViewAdapter

    }
}