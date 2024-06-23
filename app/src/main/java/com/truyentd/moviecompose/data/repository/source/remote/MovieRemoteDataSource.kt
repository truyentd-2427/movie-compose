package com.truyentd.moviecompose.data.repository.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.remote.api.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.execute
import com.truyentd.moviecompose.data.repository.source.remote.paging.SearchMoviesPagingSource
import kotlinx.coroutines.flow.Flow
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

    fun searchMovies(keyword: String): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { SearchMoviesPagingSource(authApi, keyword, 20) },
        ).flow
    }
}
