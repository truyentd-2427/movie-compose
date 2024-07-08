package com.truyentd.moviecompose.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @Expose
    @SerializedName("status_code")
    val code: Int,
    @Expose
    @SerializedName("status_message")
    val message: String,
)
