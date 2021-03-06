package com.sun.efood.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.R
import com.sun.efood.ui.home.adapter.MealCategoryAdapter
import com.sun.efood.base.BaseFragment
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.utils.RepositoryUtils
import com.sun.efood.databinding.FragmentHomeBinding
import com.sun.efood.ui.detail.MealDetailActivity
import com.sun.efood.ui.home.adapter.MealAdapter
import com.sun.efood.ui.listmeal.ListMealFragment
import com.sun.efood.ui.search.SearchFragment
import com.sun.efood.utils.addFragment
import com.sun.efood.utils.gone
import com.sun.efood.utils.show
import com.sun.efood.utils.showToast

class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeContact.View {

    private var presenter: HomePresenter? = null
    private val randomMealAdapter = MealAdapter(::onRandomMealClick)
    private val mealCategoryAdapter = MealCategoryAdapter(::onMealCategoryClick)
    private val randomMeals = mutableListOf<Meal>()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun initComponents() {
        binding.apply {
            recyclerCategory.adapter = mealCategoryAdapter
            recyclerRandomMeal.adapter = randomMealAdapter
        }
    }

    override fun initEvents() {
        binding.searchMeal.setOnClickListener {
            parentFragmentManager.addFragment(
                R.id.fragmentContainer,
                SearchFragment()
            )
        }
    }

    override fun initData() {
        presenter = HomePresenter(
            this,
            RepositoryUtils.getMealRepository(context),
            RepositoryUtils.getCategoryRepository()
        )
        presenter?.getData()
    }

    override fun showRandomMeal(meal: Meal) {
        randomMeals.add(meal)
        randomMealAdapter.updateData(randomMeals)
    }

    override fun showMealCategory(mealCategory: List<MealCategory>) {
        mealCategoryAdapter.updateData(mealCategory.toMutableList())
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

    private fun onRandomMealClick(meal: Meal) {
        startActivity(MealDetailActivity.getInstance(context, meal))
    }

    private fun onMealCategoryClick(mealCategory: MealCategory) {
        parentFragmentManager.addFragment(
            R.id.fragmentContainer,
            ListMealFragment.getInstance(mealCategory)
        )
    }
}
