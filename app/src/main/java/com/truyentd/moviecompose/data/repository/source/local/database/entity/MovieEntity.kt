package com.truyentd.moviecompose.data.repository.source.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.truyentd.moviecompose.data.model.MovieData

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,
    @SerializedName("title")
    @Expose
    val title: String? = null,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = null,
) {

    companion object {
        fun fromMovieData(movie: MovieData): MovieEntity {
            return MovieEntity(
                id = movie.id,
                posterPath = movie.posterPath,
                title = movie.title,
                voteAverage = movie.voteAverage,
            )
        }
    }
}
