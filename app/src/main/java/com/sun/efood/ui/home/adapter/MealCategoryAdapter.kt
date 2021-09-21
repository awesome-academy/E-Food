package com.sun.efood.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sun.efood.base.BaseAdapter
import com.sun.efood.base.BaseViewHolder
import com.sun.efood.data.model.MealCategory
import com.sun.efood.databinding.ItemCategoryBinding
import com.sun.efood.utils.loadImage

class MealCategoryAdapter(private val onItemClick: (MealCategory) -> Unit) :
    BaseAdapter<MealCategory, MealCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )

    class ViewHolder(
        private val viewBinding: ItemCategoryBinding,
        onItemClick: (MealCategory) -> Unit
    ) : BaseViewHolder<MealCategory>(viewBinding, onItemClick) {

        override fun onBindData(itemData: MealCategory) {
            super.onBindData(itemData)
            with(viewBinding) {
                itemData.run {
                    textNameCategory.text = name
                    imageMealCategory.loadImage(itemData.image)
                }
            }
        }
    }
}
