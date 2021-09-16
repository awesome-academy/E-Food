package com.sun.efood.data.source

import com.sun.efood.data.source.utils.OnDataCallback
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail

interface MealDataSource {

    interface Remote {
        fun getRandomMeal(callback: OnDataCallback<Meal>)
        fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>)
        fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>)
        fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<MealDetail>>)
    }

    interface Local {
        fun insertMeal(meal: Meal, callback: OnDataCallback<Long>)
        fun deleteMeal(mealId: String, callback: OnDataCallback<Boolean>)
        fun getAllMeals(callback: OnDataCallback<List<Meal>>)
        fun isFavorite(mealId: String, callback: OnDataCallback<Boolean>)
    }
}
