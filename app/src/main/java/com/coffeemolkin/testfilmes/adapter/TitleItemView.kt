package com.coffeemolkin.testfilmes.adapter

import com.coffeemolkin.testfilmes.items.IItemView

interface TitleItemView : IItemView {

    fun showTitle(title: String?)
}