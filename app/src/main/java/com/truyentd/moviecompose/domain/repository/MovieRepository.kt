package com.truyentd.moviecompose.domain.repository

import androidx.paging.PagingData
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlayingMovies(): List<MovieData>

    suspend fun getPopularMovies(): List<MovieData>

    suspend fun getMovieDetail(movieId: Int): MovieData?

    suspend fun getMovieGenre(): List<GenreData>

    suspend fun getMovieCredits(movieId: Int): List<CastData>

    fun searchMovies(keyword: String): Flow<PagingData<MovieData>>
}
