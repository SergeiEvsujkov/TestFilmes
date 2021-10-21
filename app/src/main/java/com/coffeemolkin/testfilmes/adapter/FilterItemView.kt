package com.coffeemolkin.testfilmes.adapter

import com.coffeemolkin.testfilmes.items.IItemView

interface FilterItemView : IItemView {

    fun showFilter(filter: String?, isCheck : Boolean)
    fun onClick()
}