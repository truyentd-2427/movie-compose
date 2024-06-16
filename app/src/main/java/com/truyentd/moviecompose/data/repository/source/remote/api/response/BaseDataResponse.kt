package com.truyentd.moviecompose.data.repository.source.remote.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @Expose
    @SerializedName("results")
    var data: List<T>,
    @Expose
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null,
)
