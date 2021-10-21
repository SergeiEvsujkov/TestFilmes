package com.coffeemolkin.testfilmes.data

import com.coffeemolkin.testfilmes.remote.ApiHolder

class FilmsRepo  {
    fun getFilms() = ApiHolder.apiService.getFilms()
}