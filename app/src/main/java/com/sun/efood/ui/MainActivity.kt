package com.sun.efood.ui

import android.content.Context
import android.content.Intent
import com.sun.efood.base.BaseActivity
import com.sun.efood.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreatedView() {
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
