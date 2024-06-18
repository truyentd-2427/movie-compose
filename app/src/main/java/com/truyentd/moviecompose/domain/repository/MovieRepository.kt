package com.truyentd.moviecompose.domain.repository

import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData

interface MovieRepository {
    suspend fun getNowPlayingMovies(): List<MovieData>

    suspend fun getPopularMovies(): List<MovieData>

    suspend fun getMovieDetail(movieId: Int): MovieData?

    suspend fun getMovieGenre(): List<GenreData>

    suspend fun getMovieCredits(movieId: Int): List<CastData>
}
