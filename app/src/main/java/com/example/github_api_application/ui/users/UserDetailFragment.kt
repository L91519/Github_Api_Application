package com.example.github_api_application.ui.users

import android.os.Bundle
import android.view.View
import com.example.github_api_application.R
import com.example.github_api_application.base.BaseFragment
import com.example.github_api_application.databinding.FragmentUserDetailBinding

class UserDetailFragment : BaseFragment<FragmentUserDetailBinding, UserDetailViewModel>(
    R.layout.fragment_user_detail, UserDetailViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}