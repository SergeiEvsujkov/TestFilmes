package com.coffeemolkin.testfilmes.remote

import com.coffeemolkin.testfilmes.data.Films
import retrofit2.Call
import retrofit2.http.GET

interface FilmsService {

    @GET("films.json")
    fun getFilms(): Call<Films>
}