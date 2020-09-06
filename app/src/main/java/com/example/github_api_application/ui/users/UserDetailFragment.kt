package com.example.github_api_application.ui.users

import android.os.Bundle
import android.view.View
import android.widget.BaseAdapter
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.base.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentUserDetailBinding
import com.example.github_api_application.model.vo.Repository
import com.example.github_api_application.model.vo.UserType
import com.example.github_api_application.utils.SharedPreferenceManager

class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(
    R.layout.fragment_user_detail, UserDetailViewModel::class.java
) {

    private lateinit var recyclerViewAdapter: BaseRecyclerViewAdapter

    override fun onResume() {
        super.onResume()
        viewModel.fetch()
        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.mainContentLayout.actionImageView.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.END)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerViewAdapter = object : BaseRecyclerViewAdapter() {
            override fun getItemLayoutRes() = R.layout.item_repository
        }
        binding.mainContentLayout.repositoryRecyclerView.adapter = recyclerViewAdapter
    }

    private fun subscribeUI() {
        viewModel.user.observe(this, Observer {
            SharedPreferenceManager.getInstance().userID = it.login
            viewModel.getUserRepos()
        })

        viewModel.userRepos.observe(this, Observer {
            recyclerViewAdapter.updateList(it) { { it } }
        })

        viewModel.navigateToUserList.observe(this, Observer {
            findNavController().navigate(UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment(it))
        })
    }
}