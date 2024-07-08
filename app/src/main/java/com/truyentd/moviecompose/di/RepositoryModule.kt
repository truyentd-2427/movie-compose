package com.truyentd.moviecompose.di

import com.truyentd.moviecompose.data.repository.MovieRepositoryImpl
import com.truyentd.moviecompose.data.repository.UserRepositoryImpl
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
