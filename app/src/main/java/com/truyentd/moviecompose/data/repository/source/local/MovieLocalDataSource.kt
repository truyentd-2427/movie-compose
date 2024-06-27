package com.truyentd.moviecompose.data.repository.source.local

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.local.database.dao.MovieDao
import com.truyentd.moviecompose.data.repository.source.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
) {
    fun getBookmarkMovies(): Flow<List<MovieData>> {
        return movieDao.getBookmarkMovies().map { movieEntities ->
            movieEntities.map { MovieData.fromMovieEntity(it) }
        }
    }

    suspend fun saveOrUpdateBookmarkMovie(movie: MovieData) {
        movieDao.saveOrUpdateBookmarkMovie(MovieEntity.fromMovieData(movie))
    }

    suspend fun deleteBookmarkMovie(movieId: Int) {
        movieDao.deleteBookmarkMovie(movieId)
    }

    suspend fun getBookmarkMovieById(movieId: Int): MovieData? {
        return movieDao.getBookmarkMovieById(movieId)?.run { MovieData.fromMovieEntity(this) }
    }
}
