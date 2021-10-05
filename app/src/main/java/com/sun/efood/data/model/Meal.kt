package com.sun.efood.data.model

import android.content.ContentValues
import android.database.Cursor
import android.os.Parcelable
import com.sun.efood.utils.MealModelConst.ID_MEAL
import com.sun.efood.utils.MealModelConst.STR_MEAL
import com.sun.efood.utils.MealModelConst.STR_MEAL_THUMB
import kotlinx.parcelize.Parcelize
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

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(MEAL_KEY_ID)),
        cursor.getString(cursor.getColumnIndex(MEAL_KEY_NAME)),
        cursor.getString(cursor.getColumnIndex(MEAL_KEY_IMAGE))
    )

    fun getContentValue() = ContentValues().apply {
        put(MEAL_KEY_ID, id)
        put(MEAL_KEY_NAME, name)
        put(MEAL_KEY_IMAGE, image)
    }

    companion object {
        const val MEAL_TABLE_NAME = "meal"
        const val MEAL_KEY_ID = "id"
        const val MEAL_KEY_NAME = "name"
        const val MEAL_KEY_IMAGE = "image"
    }
}
