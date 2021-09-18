package com.sun.efood.ui.splash

import android.os.Handler
import android.os.Looper
import com.sun.efood.base.BaseActivity
import com.sun.efood.databinding.ActivitySplashBinding
import com.sun.efood.ui.main.MainActivity

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
