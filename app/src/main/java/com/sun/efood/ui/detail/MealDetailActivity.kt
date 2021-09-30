package com.sun.efood.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.sun.efood.BuildConfig
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.repository.utils.RepositoryUtils
import com.sun.efood.databinding.ActivityMealDetailBinding
import com.sun.efood.utils.Constants
import com.sun.efood.utils.showToast

class MealDetailActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityMealDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private const val BUNDLE_MEAL = "BUNDLE_MEAL"

        fun getInstance(context: Context?, meal: Meal) =
            Intent(context, MealDetailActivity::class.java).apply {
                putExtra(BUNDLE_MEAL, meal)
            }
    }
}
