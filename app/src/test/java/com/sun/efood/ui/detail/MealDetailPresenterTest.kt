package com.sun.efood.ui.detail

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.lang.Exception


class MealDetailPresenterTest {
    private val view = mockk<MealDetailContact.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val mealDetailPresenter = MealDetailPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<MealDetail>>>()
    private val meal = mockMeal()
    private val mealDetails = mutableListOf(mockDetail())

    @Test
    fun `getMealDetail callback return onSuccess`() {
        every {
            repository.getMealDetail(meal.id, capture(callback))
        } answers {
            callback.captured.onSuccess(mealDetails)
        }
        mealDetailPresenter.getMealDetail(meal)
        verify {
            view.showMealDetail(mealDetails)
        }
    }

    @Test
    fun `getMealDetail callback return onFailure exception not null`() {
        val exception = Exception()
        every {
            repository.getMealDetail( meal.name, capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        mealDetailPresenter.getMealDetail(mockMeal())
        verify {
            view.showMessage(exception.message.toString())
        }
    }

    @Test
    fun `getMealDetail callback return onFailure exception null`() {
        val exception : Exception? = null
        every {
            repository.getMealDetail(meal.name, capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        mealDetailPresenter.getMealDetail(mockMeal())
        verify {
            view.showMessage(exception?.message.toString())
        }
    }

    private fun mockMeal() = Meal(
        id = "52944",
        name = "Escovitch Fish",
        image = "https://www.themealdb.com/images/media/meals/1520084413.jpg",
        )

    private fun mockDetail() = MealDetail(
        name = "Escovitch Fish",
        category = "Seafood",
        instruction = "Rinse fish",
        youtube = "https://www.youtube.com/watch?v=nF6DOtGE6k8",
        image = "https://www.themealdb.com/images/media/meals/1520084413.jpg",
        ingredient = "Red Snapper"
    )
}
