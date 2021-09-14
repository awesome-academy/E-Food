package com.sun.efood.data.source.remote.api

import android.net.Uri
import com.sun.efood.data.source.remote.api.APIQueryExtension.appendAuthorityAndContent

object ApiQuery {

    fun queryRandomMeal() = Uri.Builder()
        .appendAuthorityAndContent(ApiConstants.MEAL_RANDOM)
        .toString()

    fun queryMealCategory() = Uri.Builder()
        .appendAuthorityAndContent(ApiConstants.MEAL_CATEGORY)
        .toString()

    fun queryMealByCategory(meal: String) = Uri.Builder()
        .appendAuthorityAndContent(ApiConstants.FILTER)
        .appendQueryParameter(ApiConstants.FILTER_CATEGORY_MEAL, meal)
        .toString()

    fun queryMealDetail(idMeal: String) = Uri.Builder()
        .appendAuthorityAndContent(ApiConstants.LOOKUP)
        .appendQueryParameter(ApiConstants.FILTER_LOOKUP, idMeal)
        .toString()

    fun querySearchMeal(wordSearch: String) = Uri.Builder()
        .appendAuthorityAndContent(ApiConstants.SEARCH)
        .appendQueryParameter(ApiConstants.FILTER_SEARCH_MEAL, wordSearch)
        .toString()
}
