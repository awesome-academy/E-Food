package com.sun.efood.data.source.remote

import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.source.MealCategoryDataSource
import com.sun.efood.data.source.remote.utils.connectData
import com.sun.efood.data.source.utils.LoadDataAsync
import com.sun.efood.data.source.remote.api.ApiQuery
import com.sun.efood.data.source.utils.OnDataCallback
import com.sun.efood.utils.Constants
import com.sun.efood.utils.parseJsonToObject
import org.json.JSONObject

@Suppress("DEPRECATION")
class MealCategoryRemoteDataSource private constructor() : MealCategoryDataSource {

    override fun getMealCategories(callback: OnDataCallback<List<MealCategory>>) {
        LoadDataAsync<Unit, List<MealCategory>>(callback) {
            getMealCategories()
        }.execute(Unit)
    }

    private fun getMealCategories(): List<MealCategory> =
        JSONObject(connectData(ApiQuery.queryMealCategory()))
            .getString(Constants.CATEGORIES)
            .parseJsonToObject()

    companion object {
        private var instance: MealCategoryRemoteDataSource? = null

        fun getInstance() =
            instance ?: MealCategoryRemoteDataSource().also { instance = it }
    }
}
