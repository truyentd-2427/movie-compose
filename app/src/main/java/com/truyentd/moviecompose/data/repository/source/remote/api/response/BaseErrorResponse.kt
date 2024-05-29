package com.truyentd.moviecompose.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @Expose
    @SerializedName("code")
    val code: Int,
    @Expose
    @SerializedName("data")
    val data: Any?,
    @Expose
    @SerializedName("message")
    val message: String,
)
