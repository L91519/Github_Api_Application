package com.example.github_api_application.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.github_api_application.R
import com.example.github_api_application.databinding.FragmentWebviewBinding

abstract class BaseWebViewFragment: Fragment(R.layout.fragment_webview) {
    protected lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (!this::binding.isInitialized) {
            super.onCreateView(inflater, container, savedInstanceState)
        } else {
            binding.root
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = DataBindingUtil.bind(requireView())!!

        binding.lifecycleOwner = viewLifecycleOwner


        onViewCreated(savedInstanceState)
    }

    open fun onViewCreated(savedInstanceState: Bundle?) {
    }

}