package com.truyentd.moviecompose.data.repository.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.truyentd.moviecompose.data.repository.source.local.database.dao.MovieDao
import com.truyentd.moviecompose.data.repository.source.local.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
