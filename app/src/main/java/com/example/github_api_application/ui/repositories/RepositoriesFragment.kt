package com.example.github_api_application.ui.repositories

import android.os.Bundle
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.databinding.FragmentRepositoriesBinding
import com.example.github_api_application.model.vo.RepoType

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>(
    R.layout.fragment_repositories, RepositoriesViewModel::class.java) {

    private lateinit var repoType: RepoType

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repoType = RepositoriesFragmentArgs.fromBundle(arguments?:return).repoType
    }

}