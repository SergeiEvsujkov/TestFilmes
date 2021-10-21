package com.coffeemolkin.testfilmes.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FilmsView : MvpView {
    fun init()
    fun updateList()
}