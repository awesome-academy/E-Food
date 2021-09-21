package com.sun.efood.ui.home

import com.sun.efood.base.BasePresenter
import com.sun.efood.base.BaseView
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory

interface HomeContact {
    interface View : BaseView {
        fun showRandomMeal(meal: Meal)
        fun showMealCategory(mealCategory: List<MealCategory>)
    }

    interface Presenter : BasePresenter {
        fun getRandomMeal()
        fun getMealCategory()
    }
}
