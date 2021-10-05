package com.sun.efood.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.base.BaseFragment
import com.sun.efood.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding =
        FragmentFavoriteBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }
}
