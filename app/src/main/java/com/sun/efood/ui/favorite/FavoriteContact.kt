package com.sun.efood.ui.favorite

import com.sun.efood.base.BasePresenter
import com.sun.efood.base.BaseView
import com.sun.efood.data.model.Meal

interface FavoriteContact {
    interface View : BaseView {
        fun showMealsFavorite(meals : List<Meal>)
    }

    interface Presenter : BasePresenter {
        fun getMealsFavorite()
    }
}
