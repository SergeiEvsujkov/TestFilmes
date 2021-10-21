package com.coffeemolkin.testfilmes.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(

    @Expose
    val id: Long? = null,

    @Expose
    @SerializedName("localized_name")
    val localizedName: String? = null,


    @Expose
    val name: String? = null,

    @Expose
    val year: Long? = null,

    @Expose
    val rating: Float? = null,

    @Expose
    @SerializedName("image_url")
    val imageUrl: String? = null,

    @Expose
    val description: String? = null,

    @Expose
    val genres: MutableList<String>? = null,

    ) : Parcelable

@Parcelize
data class Films(

    @Expose
    val films: List<Film>? = null,

    ) : Parcelable

@Parcelize
data class Filter(

    val filter: String? = null,

    var isCheck: Boolean = false,

    ) : Parcelable

@Parcelize
data class Title(

    val title: String? = null,

    ) : Parcelable