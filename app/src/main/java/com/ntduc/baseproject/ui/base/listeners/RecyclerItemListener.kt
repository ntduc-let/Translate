package com.ntduc.baseproject.ui.base.listeners

import com.ntduc.baseproject.data.dto.recipes.RecipesItem

/**
 * Created by TruyenIT
 */

interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}
