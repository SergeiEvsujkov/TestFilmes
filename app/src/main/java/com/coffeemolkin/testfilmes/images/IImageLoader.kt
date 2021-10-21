package com.coffeemolkin.testfilmes.images

interface IImageLoader<T> {

    fun loadTo(url: String, target: T)
}