package com.sun.efood.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.base.BaseFragment
import com.sun.efood.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }
}
