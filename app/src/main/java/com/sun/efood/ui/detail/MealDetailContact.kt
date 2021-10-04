package com.sun.efood.ui.detail

import com.sun.efood.base.BasePresenter
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail

interface MealDetailContact {
    interface View {
        fun showMessage(messageRes: Any)
        fun showMealDetail(mealDetails: List<MealDetail>)
    }

    interface Presenter : BasePresenter {
        fun getMealDetailByMeal(meal: Meal)
    }
}
