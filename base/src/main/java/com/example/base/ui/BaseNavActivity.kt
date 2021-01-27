package com.example.base.ui

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.base.R

abstract class BaseNavActivity(@NavigationRes private val graphRes: Int): AppCompatActivity(R.layout.activity_nav) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavGraph()
    }

    private fun setNavGraph() {
        findNavController(R.id.navHostFragment).setGraph(graphRes)
    }
}