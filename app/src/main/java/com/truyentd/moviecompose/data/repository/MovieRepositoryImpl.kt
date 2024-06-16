package com.truyentd.moviecompose.data.repository

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.remote.MovieRemoteDataSource
import com.truyentd.moviecompose.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
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
}
