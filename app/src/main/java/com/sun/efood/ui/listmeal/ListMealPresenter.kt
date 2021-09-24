package com.sun.efood.ui.listmeal

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback

class ListMealPresenter(
    private val view: ListMealContact.View,
    private val mealRepository: MealRepository
) : ListMealContact.Presenter {

    override fun getListMealOfCategory(mealCategory: MealCategory) {
        view.showLoading()
        mealRepository.getMealByCategory(
            mealCategory.name,
            object : OnDataCallback<List<Meal>> {
                override fun onSuccess(data: List<Meal>) {
                    view.showMeals(data)
                    view.hideLoading()
                }

                override fun onFailure(e: Exception?) {
                    view.showMessage(e?.message.toString())
                    view.hideLoading()
                }
            }
        )
    }

    override fun getData() {
    }
}
