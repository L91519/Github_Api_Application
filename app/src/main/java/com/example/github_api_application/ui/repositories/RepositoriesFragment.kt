package com.example.github_api_application.ui.repositories

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.github_api_application.R
import com.example.base.ui.BaseFragment
import com.example.base.ui.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentRepositoriesBinding
import com.example.github_api_application.model.vo.RepoType

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>(
    R.layout.fragment_repositories, RepositoriesViewModel::class.java
) {
    private val args by navArgs<RepositoriesFragmentArgs>()
    private lateinit var repositoryRecyclerViewAdapter: BaseRecyclerViewAdapter

    override fun onResume() {
        super.onResume()
        viewModel.fetch(args.repoType, args.userID)
        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        setupRecyclerView()
        setupToolbar()
    }

    private fun subscribeUI() {
        viewModel {
            repoList.onResult {
                repositoryRecyclerViewAdapter.updateList(it) { { it } }
            }
            navigateToBack.onResult {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupToolbar() {
        binding.toolbarContentLayout.title = when (args.repoType) {
            RepoType.USER_REPO -> RepoType.USER_REPO.value
            RepoType.STARRED_REPO -> RepoType.STARRED_REPO.value
        }
    }

    private fun setupRecyclerView() {
        repositoryRecyclerViewAdapter = object : BaseRecyclerViewAdapter() {
            override fun getItemLayoutRes(): Int = R.layout.item_repository
        }
        binding.repositoryRecyclerView.adapter = repositoryRecyclerViewAdapter
    }

}