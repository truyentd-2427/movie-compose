package com.truyentd.moviecompose.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieData(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,
    @SerializedName("belongs_to_collection")
    @Expose
    val belongsToCollection: Any? = null,
    @SerializedName("budget")
    @Expose
    val budget: Int? = null,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>? = null,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreData>? = null,
    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("imdb_id")
    @Expose
    val imdbId: String? = null,
    @SerializedName("origin_country")
    @Expose
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,
    @SerializedName("overview")
    @Expose
    val overview: String? = null,
    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,
    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,
    @SerializedName("revenue")
    @Expose
    val revenue: Int? = null,
    @SerializedName("runtime")
    @Expose
    val runtime: Int? = null,
    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguageData>? = null,
    @SerializedName("status")
    @Expose
    val status: String? = null,
    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("video")
    @Expose
    val video: Boolean? = null,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = null
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$posterPath"

    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$backdropPath"

    val lengthFormatted: String
        get() = MOVIE_LENGTH_FORMAT.format((runtime ?: 0) / 60, (runtime ?: 0) % 60)

    val ratingFormatted: String
        get() = RATING_FORMAT.format(voteAverage)

    companion object {
        private const val MOVIE_LENGTH_FORMAT = "%dh %dm"
        private const val RATING_FORMAT = "%.1f/10 IMDB"
    }
}
