package com.truyentd.moviecompose.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi
import com.truyentd.moviecompose.data.repository.source.remote.api.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.NoneAuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ApiConfig
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ServiceGenerator
import com.truyentd.moviecompose.data.repository.source.remote.api.middleware.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    fun provideNoneAuthApi(gson: Gson): NoneAuthApi {
        return ServiceGenerator.generate(
            baseUrl = ApiConfig.baseUrl(),
            serviceClass = NoneAuthApi::class.java,
            gson = gson,
            authenticator = null,
            loggingInterceptor = HttpLoggingInterceptor(),
        )
    }

    @Provides
    @Singleton
    fun provideAuthApi(gson: Gson, sharedPrefApi: SharedPrefApi): AuthApi {
        return ServiceGenerator.generate(
            baseUrl = ApiConfig.baseUrl(),
            serviceClass = AuthApi::class.java,
            gson = gson,
            authenticator = null,
            interceptors = arrayOf(
                AuthInterceptor(sharedPrefApi),
            ),
            loggingInterceptor = HttpLoggingInterceptor(),
        )
    }
}
