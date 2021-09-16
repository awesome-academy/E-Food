package com.sun.efood.data.source.local.dao

import android.annotation.SuppressLint
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.Meal.Companion.MEAL_KEY_ID
import com.sun.efood.data.model.Meal.Companion.MEAL_TABLE_NAME
import com.sun.efood.data.source.local.db.AppDatabase

class MealDaoImpl private constructor(database: AppDatabase) : MealDao {

    private val writableDB = database.writableDatabase
    private val readableDB = database.readableDatabase

    override fun insertMeal(meal: Meal) =
        writableDB.insert(MEAL_TABLE_NAME, null, meal.getContentValue())

    override fun deleteMeal(idMeal: String) =
        writableDB.delete(MEAL_TABLE_NAME, "$MEAL_KEY_ID=?", arrayOf(idMeal)) > 0

    override fun getAllMeals(): List<Meal> {
        val meals = mutableListOf<Meal>()
        val cursor = readableDB.query(
            MEAL_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                meals.add(Meal(it))
            }
        }
        return meals
    }

    @SuppressLint("Recycle")
    override fun isFavorite(mealId: String): Boolean {
        val cursor = readableDB.query(
            MEAL_TABLE_NAME,
            null,
            "$MEAL_KEY_ID = ?",
            arrayOf(mealId),
            null,
            null,
            null
        )
        return cursor.count > 0
    }

    companion object {
        private var instance: MealDaoImpl? = null

        fun getInstance(database: AppDatabase): MealDaoImpl =
            instance ?: MealDaoImpl(database).also {
                instance = it
            }
    }
}
