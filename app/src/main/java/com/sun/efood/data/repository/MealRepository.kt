package com.sun.efood.data.repository

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.source.MealDataSource
import com.sun.efood.data.source.utils.OnDataCallback

class MealRepository private constructor(
    private val remote: MealDataSource.Remote
) : MealDataSource.Remote {

    override fun getRandomMeal(callback: OnDataCallback<Meal>) {
        remote.getRandomMeal(callback)
    }

    override fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>) {
        remote.getMealByCategory(nameCategory, callback)
    }

    override fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>) {
        remote.searchMeal(nameMeal, callback)
    }

    override fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<MealDetail>>) {
        remote.getMealDetailByMeal(nameMeal, callback)
    }

    companion object {
        private var instance: MealRepository? = null

        fun getInstance(remote: MealDataSource.Remote) =
            instance ?: MealRepository(remote).also { instance = it }
    }
}
