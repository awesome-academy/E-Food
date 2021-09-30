package com.sun.efood.ui.listmeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sun.efood.base.BaseFragment
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.utils.RepositoryUtils
import com.sun.efood.databinding.FragmentListMealBinding
import com.sun.efood.ui.detail.MealDetailActivity
import com.sun.efood.ui.home.adapter.MealAdapter
import com.sun.efood.utils.gone
import com.sun.efood.utils.removeFragment
import com.sun.efood.utils.show
import com.sun.efood.utils.showToast

class ListMealFragment : BaseFragment<FragmentListMealBinding>(), ListMealContact.View {

    private var presenter: ListMealPresenter? = null
    private val mealAdapter = MealAdapter(::onMealClick)

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListMealBinding =
        FragmentListMealBinding::inflate

    override fun initComponents() {
        binding.recyclerMeals.adapter = mealAdapter
    }

    override fun initEvents() {
        binding.layoutToolBar.buttonBack.setOnClickListener {
            parentFragmentManager.removeFragment(ListMealFragment())
        }
    }

    override fun initData() {
        presenter =
            ListMealPresenter(
                this,
                RepositoryUtils.getMealRepository(context)
            )
        getTransferredData()
    }

    override fun showMeals(meals: List<Meal>) {
        mealAdapter.updateData(meals.toMutableList())
    }

    override fun showMessage(messageRes: Any) {
        context?.showToast(messageRes.toString())
    }

    override fun showLoading() {
        binding.progressMealLoading.show()
    }

    override fun hideLoading() {
        binding.progressMealLoading.gone()
    }

    private fun onMealClick(meal: Meal) {
        startActivity(MealDetailActivity.getInstance(context, meal))
    }

    private fun getTransferredData() {
        arguments?.getParcelable<MealCategory>(BUNDLE_MEAL_CATEGORY)?.apply {
            presenter?.getListMealOfCategory(this)
        }
    }

    companion object {
        private const val BUNDLE_MEAL_CATEGORY = "BUNDLE_MEAL_CATEGORY"

        fun getInstance(mealCategory: Any) = ListMealFragment().apply {
            arguments = bundleOf(BUNDLE_MEAL_CATEGORY to mealCategory)
        }
    }
}
