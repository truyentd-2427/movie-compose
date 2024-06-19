package com.truyentd.moviecompose.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData

data class MovieCreditsResponse(
    @Expose
    @SerializedName("id")
    val movieId: Int,
    @Expose
    @SerializedName("cast")
    val casts: List<CastData>,
)
