package com.truyentd.moviecompose.data.repository.source.remote

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.AuthApi

class MovieRemoteDataSource(val authApi: AuthApi) {
    fun getMovies(): List<MovieData> {
        return emptyList()
    }
}
