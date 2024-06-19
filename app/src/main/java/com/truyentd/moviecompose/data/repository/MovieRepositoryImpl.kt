package com.truyentd.moviecompose.data.repository

import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.GenreData
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

    override suspend fun getMovieGenre(): List<GenreData> {
        return remoteDataSource.getMovieGenres()
    }

    override suspend fun getMovieCredits(movieId: Int): List<CastData> {
        return remoteDataSource.getMovieCredits(movieId)
    }
}
