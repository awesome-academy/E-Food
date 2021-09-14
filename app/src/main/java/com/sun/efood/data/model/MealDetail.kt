package com.sun.efood.data.model

import android.os.Parcelable
import com.sun.efood.utils.MealDetailConst.MEAL_DETAIL_CATEGORY
import com.sun.efood.utils.MealDetailConst.MEAL_DETAIL_INSTRUCTIONS
import com.sun.efood.utils.MealDetailConst.MEAL_DETAIL_YOUTUBE
import com.sun.efood.utils.MealModelConst.STR_MEAL
import com.sun.efood.utils.MealModelConst.STR_MEAL_THUMB
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class MealDetail(
    val name: String,
    val category: String,
    val instruction: String,
    val youtube: String?,
    val image: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(MEAL_DETAIL_CATEGORY),
        jsonObject.getString(MEAL_DETAIL_INSTRUCTIONS),
        jsonObject.getString(MEAL_DETAIL_YOUTUBE),
        jsonObject.getString(STR_MEAL_THUMB)
    )
}
