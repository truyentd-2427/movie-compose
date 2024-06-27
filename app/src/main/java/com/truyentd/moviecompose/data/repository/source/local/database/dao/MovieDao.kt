package com.truyentd.moviecompose.data.repository.source.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdateBookmarkMovie(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun getBookmarkMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteBookmarkMovie(movieId: Int)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getBookmarkMovieById(movieId: Int): MovieEntity?
}
