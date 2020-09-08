package com.example.github_api_application.ui.repositories

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.base.recyclerView.BaseRecyclerViewAdapter
import com.example.github_api_application.databinding.FragmentRepositoriesBinding
import com.example.github_api_application.model.vo.RepoType

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>(
    R.layout.fragment_repositories, RepositoriesViewModel::class.java
) {

    private lateinit var repositoryRecyclerViewAdapter: BaseRecyclerViewAdapter
    private lateinit var repoType: RepoType
    private lateinit var userID: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repoType = RepositoriesFragmentArgs.fromBundle(arguments ?: return).repoType
        userID = RepositoriesFragmentArgs.fromBundle(arguments ?: return).userID
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetch(userID)

        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        setupToolbar()
        setupRecyclerView()
    }

    private fun subscribeUI() {
        viewModel.repoList.observe(viewLifecycleOwner, Observer {
            repositoryRecyclerViewAdapter.updateList(it) { { it } }
        })
    }

    private fun setupToolbar() {
        binding.toolbarContentLayout.titleTextView.text = when (repoType) {
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