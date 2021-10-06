package com.sun.efood.ui.detail

import com.sun.efood.R
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback

class MealDetailPresenter(
    private val view: MealDetailContact.View,
    private val mealRepository: MealRepository
) : MealDetailContact.Presenter {

    override fun getMealDetailByMeal(meal: Meal) {
        mealRepository.getMealDetailByMeal(meal.id, object : OnDataCallback<List<MealDetail>> {
            override fun onSuccess(data: List<MealDetail>) {
                view.showMealDetail(data)
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(R.string.error_common)
            }
        })
    }

    override fun getMealFavorite(meal: Meal) {
        mealRepository.isFavorite(meal.id, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                view.showMealFavorite(data)
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(R.string.error_common)
            }
        })
    }

    override fun insertMealFavorite(meal: Meal) {
        mealRepository.insertMeal(meal, object : OnDataCallback<Long> {
            override fun onSuccess(data: Long) {
                if (data > 0) view.showMessage(R.string.text_add_to_favorite)
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(R.string.error_common)
            }
        })
    }

    override fun deleteMealFavorite(mealId: String) {
        mealRepository.deleteMeal(mealId, object : OnDataCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                if (data) view.showMessage(R.string.text_meal_deleted_from_favorite)
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(R.string.error_common)
            }
        })
    }

    override fun getData() {
    }
}
