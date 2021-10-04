package com.sun.efood.data.model

import android.os.Parcelable
import com.sun.efood.utils.MealCategoryModelConst.ID_CATEGORY
import com.sun.efood.utils.MealCategoryModelConst.STR_CATEGORY
import com.sun.efood.utils.MealCategoryModelConst.STR_CATEGORY_DESCRIPTION
import com.sun.efood.utils.MealCategoryModelConst.STR_CATEGORY_THUMB
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class MealCategory(
    val id: String,
    val name: String,
    val image: String,
    val description: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ID_CATEGORY),
        jsonObject.getString(STR_CATEGORY),
        jsonObject.getString(STR_CATEGORY_THUMB),
        jsonObject.getString(STR_CATEGORY_DESCRIPTION),
    )
}
