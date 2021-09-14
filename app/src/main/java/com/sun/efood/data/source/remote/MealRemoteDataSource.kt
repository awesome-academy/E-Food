package com.sun.efood.data.source.remote

import com.sun.efood.data.source.MealDataSource
import com.sun.efood.data.source.remote.api.ApiConstants
import com.sun.efood.data.source.remote.utils.connectData
import com.sun.efood.data.source.utils.LoadDataAsync
import com.sun.efood.data.source.utils.OnDataCallback
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.source.remote.api.ApiQuery
import com.sun.efood.data.source.utils.RandomMealAsync
import com.sun.efood.utils.Constants
import com.sun.efood.utils.parseJsonToObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class MealRemoteDataSource private constructor() : MealDataSource.Remote {

    override fun getRandomMeal(callback: OnDataCallback<Meal>) {
        RandomMealAsync(callback) {
            getRandomMeal().first()
        }.execute()
    }

    override fun getMealByCategory(nameCategory: String, callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            getMealByCategory(nameCategory)
        }.execute(Unit)
    }

    override fun searchMeal(nameMeal: String, callback: OnDataCallback<List<Meal>>) {
        LoadDataAsync<Unit, List<Meal>>(callback) {
            getMealByKeySearch(nameMeal)
        }.execute(Unit)
    }

    override fun getMealDetailByMeal(nameMeal: String, callback: OnDataCallback<List<MealDetail>>) {
        LoadDataAsync<Unit, List<MealDetail>>(callback) {
            getMealDetailByMeal(nameMeal)
        }.execute(Unit)
    }

    private fun getRandomMeal() : List<Meal> =
        JSONObject(connectData(ApiQuery.queryRandomMeal()))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealByCategory(nameCategory: String): List<Meal> =
        JSONObject(connectData(ApiQuery.queryMealByCategory(nameCategory)))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealDetailByMeal(idMeal: String): List<MealDetail> =
        JSONObject(connectData(ApiQuery.queryMealDetail(idMeal)))
            .getString(Constants.MEALS).parseJsonToObject()

    private fun getMealByKeySearch(nameMeal: String): List<Meal> {
        val json = connectData(ApiQuery.querySearchMeal(nameMeal))
        return if (json == ApiConstants.MEAL_NULL) {
            listOf()
        } else {
            JSONObject(json).getString(Constants.MEALS).parseJsonToObject()
        }
    }

    companion object {
        private var instance: MealRemoteDataSource? = null

        fun getInstance() =
            instance ?: MealRemoteDataSource().also { instance = it }
    }
}
