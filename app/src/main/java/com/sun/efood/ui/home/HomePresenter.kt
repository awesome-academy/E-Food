package com.sun.efood.ui.home

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.MealCategoryRepository
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback

class HomePresenter(
    private val view: HomeContact.View,
    private val mealRepository: MealRepository,
    private val mealCategoryRepository: MealCategoryRepository
) : HomeContact.Presenter {

    override fun getRandomMeal() {
        view.showLoading()
        mealRepository.getRandomMeal(object : OnDataCallback<Meal> {
            override fun onSuccess(data: Meal) {
                view.showRandomMeal(data)
                view.hideLoading()
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(e?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getMealCategory() {
        view.showLoading()
        mealCategoryRepository.getMealCategories(object : OnDataCallback<List<MealCategory>> {
            override fun onSuccess(data: List<MealCategory>) {
                view.showMealCategory(data)
                view.hideLoading()
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(e?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getData() {
        getRandomMeal()
        getMealCategory()
    }
}
