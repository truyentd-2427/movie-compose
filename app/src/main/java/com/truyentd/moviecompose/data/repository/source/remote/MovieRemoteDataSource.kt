package com.truyentd.moviecompose.data.repository.source.remote

import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.remote.api.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.execute
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun getNowPlayingMovies(): List<MovieData> {
        return authApi.execute { getNowPlayingMovies().data }
    }

    suspend fun getPopularMovies(): List<MovieData> {
        return authApi.execute { getPopularMovies().data }
    }

    suspend fun getMovieDetail(movieId: Int): MovieData {
        return authApi.execute { getMovieDetail(movieId) }
    }

    suspend fun getMovieGenres(): List<GenreData> {
        return authApi.execute { getMovieGenres().genres }
    }

    suspend fun getMovieCredits(movieId: Int): List<CastData> {
        return authApi.execute { getMovieCredits(movieId).casts }
    }
}
