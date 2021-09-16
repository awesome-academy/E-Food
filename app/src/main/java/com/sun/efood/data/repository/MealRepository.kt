package com.sun.efood.data.repository

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.source.MealDataSource
import com.sun.efood.data.source.utils.OnDataCallback

class MealRepository private constructor(
    private val remote: MealDataSource.Remote,
    private val local: MealDataSource.Local
) : MealDataSource.Remote, MealDataSource.Local {

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

    override fun insertMeal(meal: Meal, callback: OnDataCallback<Long>) {
        local.insertMeal(meal, callback)
    }

    override fun deleteMeal(mealId: String, callback: OnDataCallback<Boolean>) {
        local.deleteMeal(mealId, callback)
    }

    override fun getAllMeals(callback: OnDataCallback<List<Meal>>) {
        local.getAllMeals(callback)
    }

    override fun isFavorite(mealId: String, callback: OnDataCallback<Boolean>) {
        local.isFavorite(mealId, callback)
    }

    companion object {
        private var instance: MealRepository? = null

        fun getInstance(remote: MealDataSource.Remote, local: MealDataSource.Local) =
            instance ?: MealRepository(remote, local).also { instance = it }
    }
}
