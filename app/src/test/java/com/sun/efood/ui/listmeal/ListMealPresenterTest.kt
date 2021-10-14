package com.sun.efood.ui.listmeal

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Test
import java.lang.Exception

class ListMealPresenterTest {
    private val view = mockk<ListMealContact.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val presenter = ListMealPresenter(view, repository)
    private val callback = slot<OnDataCallback<List<Meal>>>()
    private val mealCategory = mockCategory()
    private val meals = mutableListOf(mockMeal())

    @Test
    fun `getListMealOfCategory callback return onSuccess`() {
        every {
            repository.getMealByCategory(mealCategory.name, capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }
        presenter.getListMealOfCategory(mealCategory)
        verify {
            view.showMeals(meals)
        }
    }

    @Test
    fun `getListMealOfCategory callback return onFailure exception not null`() {
        val exception = Exception()
        every {
            repository.getMealByCategory(mealCategory.name, capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        presenter.getListMealOfCategory(mealCategory)
        verify {
            view.showMessage(exception.message.toString())
        }
    }

    @Test
    fun `getListMealOfCategory callback return onFailure exception null`() {
        val exception : Exception? = null
        every {
            repository.getMealByCategory(mealCategory.name, capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        presenter.getListMealOfCategory(mealCategory)
        verify {
            view.showMessage(exception?.message.toString())
        }
    }

    private fun mockMeal() = Meal(
        id = "52772",
        name = "Teriyaki Chicken Casserole",
        image = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"
    )

    private fun mockCategory() = MealCategory(
        id = "2",
        name = "Chicken",
        image = "https://www.themealdb.com/images/category/beef.png",
        description = "Chicken"
    )
}
