package com.sun.efood.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sun.efood.base.BaseFragment
import com.sun.efood.databinding.FragmentMealDetailBinding

class MealDetailFragment : BaseFragment<FragmentMealDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealDetailBinding =
        FragmentMealDetailBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }

    companion object {
        private const val BUNDLE_MEAL = "BUNDLE_MEAL"

        fun getInstance(meal: Any) = MealDetailFragment().apply {
            arguments = bundleOf(BUNDLE_MEAL to meal)
        }
    }
}
