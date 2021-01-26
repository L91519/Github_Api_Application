package com.example.github_api_application.ui.users

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.base.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentUserDetailBinding
import com.example.github_api_application.utils.SharedPreferenceManager

class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(
    R.layout.fragment_user_detail, UserDetailViewModel::class.java
) {
    private lateinit var recyclerViewAdapter: BaseRecyclerViewAdapter
    private var userID: String? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userID = UserDetailFragmentArgs.fromBundle(arguments ?: return).userID
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetch(userID)
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
        viewModel {
            user.onResult {
                SharedPreferenceManager.getInstance().userID = it.login
                viewModel.getUserRepos()
            }
            userRepos.onResult {
                recyclerViewAdapter.updateList(it) { { it } }
            }
            navigateToUserList.onResult {
                binding.drawerLayout.closeDrawer(GravityCompat.END)
                findNavController().navigate(
                    UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment(
                        it,
                        viewModel.user.value?.login ?: return@onResult
                    )
                )
            }
            navigateToRepoList.onResult {
                findNavController().navigate(
                    UserDetailFragmentDirections.actionUserDetailFragmentToRepositoriesFragment(
                        it,
                        viewModel.user.value?.login ?: return@onResult
                    )
                )
            }
            navigateToBack.onResult {
                findNavController().navigateUp()
            }
        }
    }
}