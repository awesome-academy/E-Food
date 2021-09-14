package com.sun.efood.utils

import com.sun.efood.R
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.model.MealDetail
import org.json.JSONArray
import org.json.JSONException

inline fun <reified T> String.parseJsonToObject() = JSONArray(this).run {
    MutableList(length()) { index ->
        when (T::class) {
            Meal::class -> Meal(getJSONObject(index)) as T
            MealCategory::class -> MealCategory(getJSONObject(index)) as T
            MealDetail::class -> MealDetail(getJSONObject(index)) as T
            else -> throw JSONException(getString(R.string.error_common))
        }
    }
}
