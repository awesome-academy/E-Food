package com.sun.efood.ui.detail

import com.sun.efood.base.BasePresenter
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail

interface MealDetailContact {
    interface View {
        fun showMessage(messageRes: Int)
        fun showMealDetail(mealDetails: List<MealDetail>)
        fun showMealFavorite(isFavorite: Boolean)
    }

    interface Presenter : BasePresenter {
        fun getMealDetailByMeal(meal: Meal)
        fun getMealFavorite(meal: Meal)
        fun insertMealFavorite(meal: Meal)
        fun deleteMealFavorite(mealId: String)
    }
}
