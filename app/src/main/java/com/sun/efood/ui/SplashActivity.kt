package com.sun.efood.ui

import android.os.Handler
import android.os.Looper
import com.sun.efood.base.BaseActivity
import com.sun.efood.databinding.ActivitySplashBinding

private const val TIME_WAITING = 1500L

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreatedView() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(MainActivity.getIntent(this))
            finish()
        }, TIME_WAITING)
    }
}
