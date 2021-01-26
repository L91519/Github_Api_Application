package com.example.github_api_application.ui.users

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.base.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentUsersBinding
import com.example.github_api_application.model.vo.UserType

class UsersFragment : BaseFragment<FragmentUsersBinding, UsersViewModel>(
    R.layout.fragment_users, UsersViewModel::class.java) {

    private val args by navArgs<UsersFragmentArgs>()
    private lateinit var userRecyclerViewAdapter: BaseRecyclerViewAdapter

    override fun onResume() {
        super.onResume()
        viewModel.fetch(args.userType, args.userID)

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.toolbarContentLayout.title = when (args.userType) {
            UserType.FOLLOWER -> UserType.FOLLOWER.value
            UserType.FOLLOWING -> UserType.FOLLOWING.value
            UserType.STARGAZER -> UserType.STARGAZER.value
        }
        setupRecyclerView()
    }

    private fun subscribeUI() {
        viewModel {
            userList.onResult {
                userRecyclerViewAdapter.updateList(it) { { it } }
            }
            navigateToBack.onResult {
                findNavController().navigateUp()
            }
            navigateToUserDetail.onResult {
                findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToUserDetailFragment(it.login))
            }
        }
    }

    private fun setupRecyclerView() {
        userRecyclerViewAdapter = UserListAdapter()
        userRecyclerViewAdapter.viewModel = viewModel
        binding.userRecyclerView.adapter = userRecyclerViewAdapter

    }
}