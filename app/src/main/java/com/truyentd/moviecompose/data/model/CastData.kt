package com.truyentd.moviecompose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastData(
    @Expose
    @SerializedName("adult")
    val adult: Boolean? = null,
    @Expose
    @SerializedName("gender")
    val gender: Int? = null,
    @Expose
    @SerializedName("id")
    val id: Int? = null,
    @Expose
    @SerializedName("known_for_department")
    val knownForDepartment: String? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null,
    @Expose
    @SerializedName("original_name")
    val originalName: String? = null,
    @Expose
    @SerializedName("popularity")
    val popularity: Double? = null,
    @Expose
    @SerializedName("profile_path")
    val profilePath: String? = null,
    @Expose
    @SerializedName("cast_id")
    val castId: Int? = null,
    @Expose
    @SerializedName("character")
    val character: String? = null,
    @Expose
    @SerializedName("credit_id")
    val creditId: String? = null,
    @Expose
    @SerializedName("order")
    val order: Int? = null
) {
    val profileImageUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$profilePath"
}
