package com.truyentd.moviecompose.data.di

import com.truyentd.moviecompose.data.repository.MovieRepositoryImpl
import com.truyentd.moviecompose.data.repository.source.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.MovieRemoteDataSource
import com.truyentd.moviecompose.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Provides
    fun provideMovieRemoteDataSource(authApi: AuthApi): MovieRemoteDataSource {
        return MovieRemoteDataSource(authApi)
    }

    @Binds
    abstract fun provideMovieRepository(remoteDataSourceImpl: MovieRepositoryImpl): MovieRepository
}
