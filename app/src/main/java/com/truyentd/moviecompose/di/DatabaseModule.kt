package com.truyentd.moviecompose.di

import android.app.Application
import androidx.room.Room
import com.truyentd.moviecompose.data.repository.source.local.database.MovieDatabase
import com.truyentd.moviecompose.data.repository.source.local.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, MOVIE_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

    companion object {
        private const val MOVIE_DATABASE = "MovieCompose.db"
    }
}
