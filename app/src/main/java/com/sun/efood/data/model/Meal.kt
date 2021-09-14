package com.sun.efood.data.model

import android.os.Parcelable
import com.sun.efood.utils.MealModelConst.ID_MEAL
import com.sun.efood.utils.MealModelConst.STR_MEAL
import com.sun.efood.utils.MealModelConst.STR_MEAL_THUMB
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Meal(
    val id: String,
    val name: String,
    val image: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_MEAL),
        jsonObject.getString(STR_MEAL),
        jsonObject.getString(STR_MEAL_THUMB)
    )
}
