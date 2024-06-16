package com.truyentd.moviecompose.data.repository.source.remote

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.execute

class MovieRemoteDataSource(private val authApi: AuthApi) {
    suspend fun getNowPlayingMovies(): List<MovieData> {
        return authApi.execute { getNowPlayingMovies().data }
    }

    suspend fun getPopularMovies(): List<MovieData> {
        return authApi.execute { getPopularMovies().data }
    }

    suspend fun getMovieDetail(movieId: Int): MovieData {
        return authApi.execute { getMovieDetail(movieId) }
    }
}
