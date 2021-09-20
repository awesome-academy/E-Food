package com.sun.efood.ui.listmeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sun.efood.base.BaseFragment
import com.sun.efood.databinding.FragmentListMealBinding

class ListMealFragment : BaseFragment<FragmentListMealBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListMealBinding =
        FragmentListMealBinding::inflate

    override fun initComponents() {
    }

    override fun initEvents() {
    }

    override fun initData() {
    }

    companion object {
        private const val BUNDLE_MEAL_CATEGORY = "BUNDLE_MEAL_CATEGORY"

        fun getInstance(mealCategory: Any) = ListMealFragment().apply {
            arguments = bundleOf(BUNDLE_MEAL_CATEGORY to mealCategory)
        }
    }
}
