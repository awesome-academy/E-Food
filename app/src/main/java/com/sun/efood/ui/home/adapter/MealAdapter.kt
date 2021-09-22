package com.sun.efood.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.R
import com.sun.efood.base.BaseAdapter
import com.sun.efood.base.BaseViewHolder
import com.sun.efood.data.model.Meal
import com.sun.efood.databinding.ItemMealBinding
import com.sun.efood.utils.loadImage

class MealAdapter(
    private val onItemClick: (Meal) -> Unit
) : BaseAdapter<Meal, MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )

    class ViewHolder(
        private val viewBinding: ItemMealBinding,
        onItemClick: (Meal) -> Unit
    ) : BaseViewHolder<Meal>(viewBinding, onItemClick) {

        override fun onBindData(itemData: Meal) {
            super.onBindData(itemData)
            with(viewBinding) {
                itemData.run {
                    textTitleMeal.text = name
                    imageMeal.loadImage(itemData.image)
                    imageFavorite.setOnClickListener {
                        imageFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
            }
        }
    }
}
