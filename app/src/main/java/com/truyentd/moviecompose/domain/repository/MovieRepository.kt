package com.truyentd.moviecompose.domain.repository

import com.truyentd.moviecompose.data.model.MovieData

interface MovieRepository {
    suspend fun getNowPlayingMovies(): List<MovieData>

    suspend fun getPopularMovies(): List<MovieData>

    suspend fun getMovieDetail(movieId: Int): MovieData?
}
