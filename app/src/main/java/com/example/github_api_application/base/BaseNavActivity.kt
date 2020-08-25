package com.example.github_api_application.base

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.github_api_application.R

abstract class BaseNavActivity(@NavigationRes private val graphRes: Int): AppCompatActivity(R.layout.activity_nav) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavGraph()
    }

    private fun setNavGraph() {
        findNavController(R.id.navHostFragment).setGraph(graphRes)
    }
}