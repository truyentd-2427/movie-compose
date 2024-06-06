package com.truyentd.moviecompose.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.truyentd.moviecompose.data.repository.source.NoneAuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ApiConfig
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    fun provideNoneAuthApi(gson: Gson): NoneAuthApi {
        return ServiceGenerator.generate(
            baseUrl = ApiConfig.baseUrl(),
            serviceClass = NoneAuthApi::class.java,
            gson = gson,
            authenticator = null,
            loggingInterceptor = HttpLoggingInterceptor(),
        )
    }
}
