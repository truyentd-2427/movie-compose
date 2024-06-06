package com.truyentd.moviecompose.di

import com.truyentd.moviecompose.data.repository.MovieRepositoryImpl
import com.truyentd.moviecompose.data.repository.source.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.MovieRemoteDataSource
import com.truyentd.moviecompose.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMovieRemoteDataSource(authApi: AuthApi): MovieRemoteDataSource {
        return MovieRemoteDataSource(authApi)
    }

    @Provides
    fun provideMovieRepository(remoteDataSource: MovieRemoteDataSource): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource)
    }
}
