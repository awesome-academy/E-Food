package com.sun.efood.ui.home

import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealCategory
import com.sun.efood.data.repository.MealCategoryRepository
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Test
import java.lang.Exception

class HomePresenterTest {
    private val view = mockk<HomeContact.View>(relaxed = true)
    private val mealRepository = mockk<MealRepository>()
    private val mealCategoryRepository = mockk<MealCategoryRepository>()
    private val homePresenter = HomePresenter(view, mealRepository, mealCategoryRepository)
    private val callbackRandom = slot<OnDataCallback<Meal>>()
    private val callbackCategories = slot<OnDataCallback<List<MealCategory>>>()

    @Test
    fun `getRandomMeal callback return onSuccess`() {
        val meal = mockMeal()
        every {
            mealRepository.getRandomMeal(capture(callbackRandom))
        } answers {
            callbackRandom.captured.onSuccess(meal)
        }
        homePresenter.getRandomMeal()
        verify {
            view.showRandomMeal(meal)
        }
    }

    @Test
    fun `getRandomMeal callback return onFailure exception not null`() {
        val exception = Exception()
        every {
            mealRepository.getRandomMeal(capture(callbackRandom))
        } answers {
            callbackRandom.captured.onFailure(exception)
        }
        homePresenter.getRandomMeal()
        verify {
            view.showMessage(exception.message.toString())
        }
    }

    @Test
    fun `getRandomMeal callback return onFailure exception null`() {
        val exception : Exception? = null
        every {
            mealRepository.getRandomMeal(capture(callbackRandom))
        } answers {
            callbackRandom.captured.onFailure(exception)
        }
        homePresenter.getRandomMeal()
        verify {
            view.showMessage(exception?.message.toString())
        }
    }

    @Test
    fun `getMealCategory callback return onSuccess`() {
        val mealCategories = mutableListOf(mockCategory())
        every {
            mealCategoryRepository.getMealCategories(capture(callbackCategories))
        } answers {
            callbackCategories.captured.onSuccess(mealCategories)
        }
        homePresenter.getMealCategory()
        verify {
            view.showMealCategory(mealCategories)
        }
    }

    @Test
    fun `getMealCategory callback return onFailure exception not null`() {
        val exception = Exception()
        every {
            mealCategoryRepository.getMealCategories(capture(callbackCategories))
        } answers {
            callbackCategories.captured.onFailure(exception)
        }
        homePresenter.getMealCategory()
        verify {
            view.showMessage(exception.message.toString())
        }
    }

    @Test
    fun `getMealCategory callback return onFailure exception null`() {
        val exception : Exception? = null
        every {
            mealCategoryRepository.getMealCategories(capture(callbackCategories))
        } answers {
            callbackCategories.captured.onFailure(exception)
        }
        homePresenter.getMealCategory()
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
