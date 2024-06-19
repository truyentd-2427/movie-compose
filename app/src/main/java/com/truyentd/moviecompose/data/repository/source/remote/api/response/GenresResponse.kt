package com.truyentd.moviecompose.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.truyentd.moviecompose.data.model.GenreData

data class GenresResponse(
    @Expose
    @SerializedName("genres")
    val genres: List<GenreData>
)
