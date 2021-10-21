package com.coffeemolkin.testfilmes.navigation

import com.coffeemolkin.testfilmes.ui.DescriptionFragment
import com.coffeemolkin.testfilmes.ui.FilmsFragment
import com.coffeemolkin.testfilmes.data.Film

import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class FilmsScreens : SupportAppScreen() {

        override fun getFragment() = FilmsFragment()
    }

    class DescriptionScreens(

        private val film: Film

    ) : SupportAppScreen() {

        override fun getFragment() = DescriptionFragment.newInstance(film)
    }


}