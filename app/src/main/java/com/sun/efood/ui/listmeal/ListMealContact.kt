package com.sun.efood.ui.listmeal

import com.sun.efood.base.BasePresenter
import com.sun.efood.base.BaseView
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory

interface ListMealContact {
    interface View : BaseView {
        fun showMeals(meals: List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getListMealOfCategory(mealCategory: MealCategory)
    }
}
