package com.sun.efood.ui.main

import android.content.Context
import android.content.Intent
import com.google.android.material.navigation.NavigationBarView
import com.sun.efood.R
import com.sun.efood.base.BaseActivity
import com.sun.efood.databinding.ActivityMainBinding
import com.sun.efood.ui.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val onBottomNavigation = NavigationBarView.OnItemSelectedListener {
        when (it.itemId) {
            R.id.menuHome -> openFragment(HomeFragment())
        }
        true
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreatedView() {
        binding.bottomNavigationView.apply {
            setOnItemSelectedListener(onBottomNavigation)
            selectedItemId = R.id.menuHome
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
