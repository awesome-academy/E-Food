package com.sun.efood.data.source.remote.api

import android.net.Uri

object ApiConstants {
    const val SCHEME_HTTPS = "https"
    const val AUTHORITY_API = "www.themealdb.com"
    const val CONTENT = "api/json/v1/1"
    const val CONTENT_IMAGE = "images/ingredients"

    const val MEAL_RANDOM = "random.php"
    const val MEAL_CATEGORY = "categories.php"
    const val LIST = "list.php"
    const val SEARCH = "search.php"
    const val LOOKUP = "lookup.php"
    const val FILTER = "filter.php"

    const val FILTER_SEARCH_MEAL = "s"
    const val FILTER_LOOKUP = "i"
    const val FILTER_CATEGORY_MEAL = "c"
    const val VALUE_LIST = "list"
    const val MEAL_NULL = "{\"meals\":null}"
    const val PNG = ".png"
}

object APIQueryExtension {

    fun Uri.Builder.appendAuthorityAndContent(apiPath: String) : Uri.Builder {
        return scheme(ApiConstants.SCHEME_HTTPS)
            .authority(ApiConstants.AUTHORITY_API)
            .appendPath(ApiConstants.CONTENT)
            .appendPath(apiPath)
    }
}
