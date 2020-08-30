package com.example.github_api_application.ui.repositories

import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.databinding.FragmentRepositoriesBinding

class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>(
    R.layout.fragment_repositories,
    RepositoriesViewModel::class.java
)