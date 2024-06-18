package com.truyentd.moviecompose.data.repository.source.remote.api

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.remote.api.response.BaseListResponse
import com.truyentd.moviecompose.data.repository.source.remote.api.response.GenresResponse
import com.truyentd.moviecompose.data.repository.source.remote.api.response.MovieCreditsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(): BaseListResponse<MovieData>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): BaseListResponse<MovieData>

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(@Path("id") movieId: Int): MovieData

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(): GenresResponse

    @GET("/3/movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): MovieCreditsResponse
}
