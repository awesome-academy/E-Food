package com.sun.efood.ui.favorite

import com.sun.efood.data.model.Meal
import com.sun.efood.data.repository.MealRepository
import com.sun.efood.data.source.utils.OnDataCallback
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify

import org.junit.Test

class FavoritePresenterTest {
    private val view = mockk<FavoriteContact.View>(relaxed = true)
    private val repository = mockk<MealRepository>()
    private val presenter = FavoritePresenter(view, repository)
    private val callback = slot<OnDataCallback<List<Meal>>>()

    @Test
    fun `getMealsFavorite callback return onSuccess`() {
        val meals = mockk<List<Meal>>()
        every {
            repository.getAllMeals(capture(callback))
        } answers {
            callback.captured.onSuccess(meals)
        }
        presenter.getMealsFavorite()
        verify {
            view.showMealsFavorite(meals)
            view.hideLoading()
        }
    }

    @Test
    fun `getMealsFavorite callback return onFailure exception not null`() {
        val exception = Exception()
        every {
           repository.getAllMeals(capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        presenter.getMealsFavorite()
        verify {
            view.showMessage(exception.message.toString())
            view.hideLoading()
        }
    }

    @Test
    fun `getMealsFavorite callback return onFailure exception null`() {
        val exception : Exception? = null
        every {
            repository.getAllMeals(capture(callback))
        } answers {
            callback.captured.onFailure(exception)
        }
        presenter.getMealsFavorite()
        verify {
            view.showMessage(exception?.message.toString())
            view.hideLoading()
        }
    }
}
