package com.sun.efood.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.R
import com.sun.efood.base.BaseFragment
import com.sun.efood.data.model.Meal
import com.sun.efood.data.repository.utils.RepositoryUtils
import com.sun.efood.databinding.FragmentFavoriteBinding
import com.sun.efood.ui.detail.MealDetailActivity
import com.sun.efood.ui.home.adapter.MealAdapter
import com.sun.efood.utils.gone
import com.sun.efood.utils.show
import com.sun.efood.utils.showToast

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(), FavoriteContact.View {

    private var presenter: FavoriteContact.Presenter? = null
    private val mealFavoriteAdapter = MealAdapter(::onItemClick)

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteBinding =
        FragmentFavoriteBinding::inflate

    override fun initComponents() {
        binding.apply {
            textTitleToolBar.text = getString(R.string.title_favorite)
            recyclerMealsFavorite.adapter = mealFavoriteAdapter
        }
    }

    override fun initEvents() {
    }

    override fun initData() {
        presenter =
            FavoritePresenter(
                this,
                RepositoryUtils.getMealRepository(context)
            )
        presenter?.getData()
    }

    override fun showMealsFavorite(meals: List<Meal>) {
        mealFavoriteAdapter.updateData(meals.toMutableList())
    }

    override fun showMessage(messageRes: Any) {
        context?.showToast(messageRes.toString())
    }

    override fun showLoading() {
        binding.progressFavorite.show()
    }

    override fun hideLoading() {
        binding.progressFavorite.gone()
    }

    private fun onItemClick(meal: Meal) {
        startActivity(MealDetailActivity.getInstance(context, meal))
    }
}
