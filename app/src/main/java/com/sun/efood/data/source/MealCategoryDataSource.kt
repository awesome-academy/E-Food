package com.sun.efood.data.source

import com.sun.efood.data.source.utils.OnDataCallback
import com.sun.efood.data.model.MealCategory

interface MealCategoryDataSource {
    fun getMealCategories(callback: OnDataCallback<List<MealCategory>>)
}
