package com.truyentd.moviecompose.data.repository.source.remote.api.helper

import com.google.gson.Gson
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceGenerator {
    private const val CONNECT_TIMEOUT = 30000L
    private const val READ_TIMEOUT = 30000L
    private const val WRITE_TIMEOUT = 30000L

    fun <T> generate(
        baseUrl: String,
        serviceClass: Class<T>,
        gson: Gson,
        authenticator: Authenticator?,
        loggingInterceptor: HttpLoggingInterceptor?,
        vararg interceptors: Interceptor,
    ): T {
        val okHttpBuilder = OkHttpClient().newBuilder()
        if (authenticator != null) {
            okHttpBuilder.authenticator(authenticator)
        }
        for (it in interceptors) {
            okHttpBuilder.addInterceptor(it)
        }
        // DON'T add other interceptor below logging interceptor.
        if (loggingInterceptor != null) {
            okHttpBuilder.addInterceptor { chain ->
                val origin = chain.request()
                val request = origin.newBuilder().build()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                chain.proceed(request)
            }
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpBuilder.build())
            .build()
        return retrofit.create(serviceClass)
    }
}
