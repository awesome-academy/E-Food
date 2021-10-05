package com.sun.efood.ui.detail

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
                view.showMessage(e?.message.toString())
            }
        })
    }

    override fun getData() {
    }
}
