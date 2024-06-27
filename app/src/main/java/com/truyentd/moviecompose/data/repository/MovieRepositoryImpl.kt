package com.truyentd.moviecompose.data.repository

import androidx.paging.PagingData
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.local.MovieLocalDataSource
import com.truyentd.moviecompose.data.repository.source.remote.MovieRemoteDataSource
import com.truyentd.moviecompose.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override suspend fun getNowPlayingMovies(): List<MovieData> {
        return remoteDataSource.getNowPlayingMovies()
    }

    override suspend fun getPopularMovies(): List<MovieData> {
        return remoteDataSource.getPopularMovies()
    }

    override suspend fun getMovieDetail(movieId: Int): MovieData {
        return remoteDataSource.getMovieDetail(movieId)
    }

    override suspend fun getMovieGenre(): List<GenreData> {
        return remoteDataSource.getMovieGenres()
    }

    override suspend fun getMovieCredits(movieId: Int): List<CastData> {
        return remoteDataSource.getMovieCredits(movieId)
    }

    override fun searchMovies(keyword: String): Flow<PagingData<MovieData>> {
        return remoteDataSource.searchMovies(keyword)
    }

    override fun getBookmarkMovies(): Flow<List<MovieData>> {
        return localDataSource.getBookmarkMovies()
    }

    override suspend fun saveBookmarkMovie(movie: MovieData) {
        localDataSource.saveOrUpdateBookmarkMovie(movie)
    }

    override suspend fun deleteBookmarkMovie(movieId: Int) {
        localDataSource.deleteBookmarkMovie(movieId)
    }

    override suspend fun hasBookmarkMovies(movieId: Int): Boolean {
        return localDataSource.getBookmarkMovieById(movieId) != null
    }
}
