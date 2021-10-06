package com.sun.efood.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.sun.efood.BuildConfig
import com.sun.efood.data.model.Meal
import com.sun.efood.data.model.MealDetail
import com.sun.efood.data.repository.utils.RepositoryUtils
import com.sun.efood.databinding.ActivityMealDetailBinding
import com.sun.efood.utils.Constants
import com.sun.efood.utils.gone
import com.sun.efood.utils.show
import com.sun.efood.utils.showToast

class MealDetailActivity : YouTubeBaseActivity(), MealDetailContact.View {

    private lateinit var binding: ActivityMealDetailBinding
    private var presenter: MealDetailPresenter? = null
    private var meal: Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initComponents()
        initEvent()
    }

    override fun showMealDetail(mealDetails: List<MealDetail>) {
        val meal = mealDetails.firstOrNull() ?: return
        binding.apply {
            textNameMeal.text = meal.name
            textDescriptionContent.text = meal.instruction
            textIngredients.text = meal.ingredient
            showVideo(getIdVideo(meal.youtube))
        }
    }

    override fun showMealFavorite(isFavorite: Boolean) {
        with(binding) {
            if (isFavorite) {
                imageFavorite.show()
                imageUnFavorite.gone()
            } else {
                imageFavorite.gone()
                imageUnFavorite.show()
            }
        }
    }

    override fun showMessage(messageRes: Int) {
        showToast(resources.getString(messageRes))
    }
    private fun initComponents() {
        binding.toolBar.textTitleToolBar.text = meal?.name
    }

    private fun initData() {
        meal = intent.getParcelableExtra(BUNDLE_MEAL) as Meal?
        presenter = MealDetailPresenter(
            this,
            RepositoryUtils.getMealRepository(this)
        )
        meal?.let { meal ->
            presenter?.apply {
                getMealDetailByMeal(meal)
                getMealFavorite(meal)
            }
        }
    }

    private fun initEvent() = with(binding) {
        toolBar.buttonBack.setOnClickListener {
            onBackPressed()
            finish()

        }
        imageFavorite.setOnClickListener {
            imageUnFavorite.show()
            it.gone()
            meal?.let { meal -> presenter?.deleteMealFavorite(meal.id) }
        }
        imageUnFavorite.setOnClickListener {
            imageFavorite.show()
            it.gone()
            meal?.let { meal -> presenter?.insertMealFavorite(meal) }
        }
    }

    private fun showVideo(link: String?) {
        binding.youTubePlayerView.initialize(
            BuildConfig.API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    youtubePlayer: YouTubePlayer?,
                    restored: Boolean
                ) {
                    if (!restored) {
                        youtubePlayer?.apply {
                            cueVideo(link)
                            play()
                        }
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                }
            })
    }

    private fun getIdVideo(linkVideo: String?): String {
        return if (linkVideo?.isNotEmpty() == true) {
            linkVideo.split(Constants.BASE_URL_VIDEO).elementAt(1)
        } else {
            linkVideo.toString()
        }
    }

    companion object {
        private const val BUNDLE_MEAL = "BUNDLE_MEAL"

        fun getInstance(context: Context?, meal: Meal) =
            Intent(context, MealDetailActivity::class.java).apply {
                putExtra(BUNDLE_MEAL, meal)
            }
    }
}
