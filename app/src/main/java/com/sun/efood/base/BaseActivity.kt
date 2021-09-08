package com.sun.efood.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.sun.efood.R

abstract class BaseActivity<viewBinding : ViewBinding>() : AppCompatActivity() {

    lateinit var binding: viewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onCreatedView()
    }

    protected abstract fun getViewBinding(): viewBinding
    protected abstract fun onCreatedView()

    protected fun openFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
}
