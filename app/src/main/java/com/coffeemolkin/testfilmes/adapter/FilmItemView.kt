package com.coffeemolkin.testfilmes.adapter

import com.coffeemolkin.testfilmes.items.IItemView

interface FilmItemView : IItemView {

    fun showPicture(url : String?)
    fun showTitle(url : String?)
    fun onClick()
}

