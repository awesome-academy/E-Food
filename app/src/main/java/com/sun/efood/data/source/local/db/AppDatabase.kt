package com.sun.efood.data.source.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sun.efood.data.model.Meal.Companion.MEAL_KEY_ID
import com.sun.efood.data.model.Meal.Companion.MEAL_KEY_IMAGE
import com.sun.efood.data.model.Meal.Companion.MEAL_KEY_NAME
import com.sun.efood.data.model.Meal.Companion.MEAL_TABLE_NAME

class AppDatabase private constructor(
    context: Context?,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_MEAL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DROP_MEAL_TABLE)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "dmeal.db"
        private const val DB_VERSION = 1

        private const val CREATE_MEAL_TABLE =
            "CREATE TABLE $MEAL_TABLE_NAME (" +
                    "$MEAL_KEY_ID INTEGER PRIMARY KEY, " +
                    "$MEAL_KEY_NAME TEXT, " +
                    "$MEAL_KEY_IMAGE TEXT)"

        private val DROP_MEAL_TABLE =
            String.format("DROP TABLE IF EXISTS %s", MEAL_TABLE_NAME)

        private val lock = Any()
        private var instance: AppDatabase? = null

        fun getInstance(context: Context?) =
            instance ?: synchronized(lock) {
                instance ?: AppDatabase(context, DB_NAME, DB_VERSION).also { instance = it }
            }
    }
}
