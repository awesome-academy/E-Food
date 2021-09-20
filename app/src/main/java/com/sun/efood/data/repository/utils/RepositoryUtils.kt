package com.sun.efood.data.repository.utils

import android.content.Context
import com.sun.efood.data.repository.MealCategoryRepository
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.local.MealLocalDataSource
import com.sun.efood.data.source.local.dao.MealDaoImpl
import com.sun.efood.data.source.local.db.AppDatabase
import com.sun.efood.data.source.remote.MealCategoryRemoteDataSource
import com.sun.efood.data.source.remote.MealRemoteDataSource

object RepositoryUtils {

    fun getCategoryRepository() = MealCategoryRepository
        .getInstance(MealCategoryRemoteDataSource.getInstance())

    fun getMealRepository(context: Context?) = MealRepository
        .getInstance(
            MealRemoteDataSource.getInstance(),
            MealLocalDataSource.getInstance(
                MealDaoImpl.getInstance(
                    AppDatabase.getInstance(
                        context
                    )
                )
            )
        )
}

