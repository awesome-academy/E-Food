package com.sun.efood.data.source.local.dao

import com.sun.efood.data.model.Meal

interface MealDao {
    fun insertMeal(meal: Meal): Long
    fun deleteMeal(idMeal: String): Boolean
    fun getAllMeals(): List<Meal>
    fun isFavorite(mealId: String): Boolean
}
