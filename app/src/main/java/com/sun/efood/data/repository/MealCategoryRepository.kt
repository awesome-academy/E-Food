package com.sun.efood.data.repository

import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.source.MealCategoryDataSource
import com.sun.efood.data.source.utils.OnDataCallback

class MealCategoryRepository private constructor(
    private val remote: MealCategoryDataSource
) : MealCategoryDataSource {

    override fun getMealCategories(callback: OnDataCallback<List<MealCategory>>) {
        remote.getMealCategories(callback)
    }

    companion object {
        private var instance: MealCategoryRepository? = null

        fun getInstance(remote: MealCategoryDataSource) =
            instance ?: MealCategoryRepository(remote).also { instance = it }
    }
}
