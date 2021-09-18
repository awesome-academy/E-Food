package com.sun.efood.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.base.BaseFragment
import com.sun.efood.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }
}
