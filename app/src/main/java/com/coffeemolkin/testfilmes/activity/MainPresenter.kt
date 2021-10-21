package com.coffeemolkin.testfilmes.activity

import com.coffeemolkin.testfilmes.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.FilmsScreens())
    }

    fun backPressed() {
        router.exit()
    }
}