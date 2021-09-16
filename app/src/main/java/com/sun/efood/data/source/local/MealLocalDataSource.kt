package com.sun.efood.data.source.local

import com.sun.efood.data.model.Meal
import com.sun.efood.data.source.MealDataSource
import com.sun.efood.data.source.local.dao.MealDao
import com.sun.efood.data.source.utils.LoadDataAsync
import com.sun.efood.data.source.utils.OnDataCallback

@Suppress("DEPRECATION")
class MealLocalDataSource private constructor(
    private val mealDao: MealDao
) : MealDataSource.Local {

    override fun insertMeal(meal: Meal, callback: OnDataCallback<Long>) {
        LoadDataAsync<Meal, Long>(callback) {
            mealDao.insertMeal(meal)
        }.execute(meal)
    }

    override fun deleteMeal(mealId: String, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<String, Boolean>(callback) {
            mealDao.deleteMeal(mealId)
        }.execute(mealId)
    }

    override fun getAllMeals(callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            mealDao.getAllMeals()
        }.execute(Unit)
    }

    override fun isFavorite(mealId: String, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<String, Boolean>(callback) {
            mealDao.isFavorite(mealId)
        }.execute(mealId)
    }

    companion object {
        private var instance: MealLocalDataSource? = null

        fun getInstance(mealDao: MealDao) =
            instance ?: MealLocalDataSource(mealDao).also { instance = it }
    }
}
