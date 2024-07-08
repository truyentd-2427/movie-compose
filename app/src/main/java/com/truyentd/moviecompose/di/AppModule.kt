package com.truyentd.moviecompose.di

import android.content.Context
import com.google.gson.Gson
import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi
import com.truyentd.moviecompose.data.repository.source.local.api.pref.SharedPrefApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreference(
        @ApplicationContext context: Context,
        gson: Gson,
    ): SharedPrefApi {
        return SharedPrefApiImpl(context, gson)
    }
}
