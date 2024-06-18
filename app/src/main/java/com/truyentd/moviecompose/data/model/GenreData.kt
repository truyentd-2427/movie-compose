package com.truyentd.moviecompose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GenreData(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
)
