package com.coffeemolkin.testfilmes.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {
    val gson = GsonBuilder()
        .create()

    private const val baseUrl = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

    val apiService: FilmsService by lazy {

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FilmsService::class.java)
    }


}