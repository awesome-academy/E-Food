package com.sun.efood.ui.favorite

import com.sun.efood.R
import com.sun.efood.data.model.Meal
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback

class FavoritePresenter(
    private val view: FavoriteContact.View,
    private val repository: MealRepository
) : FavoriteContact.Presenter {

    override fun getMealsFavorite() {
        view.showLoading()
        repository.getAllMeals(object : OnDataCallback<List<Meal>>{
            override fun onSuccess(data: List<Meal>) {
                view.showMealsFavorite(data)
                view.hideLoading()
            }

            override fun onFailure(e: Exception?) {
                view.showMessage(e?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getData() {
        getMealsFavorite()
    }
}
