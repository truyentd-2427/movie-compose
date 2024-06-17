package com.truyentd.moviecompose.data.repository.source.remote.api.middleware

import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ApiConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        newRequestBuilder.header(
            ApiConfig.AUTHORIZATION,
            ApiConfig.getBearerToken("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MjdiNWFhOGE0NmFmMGE5OGUyYjM5OTIxMThmNDYzYiIsInN1YiI6IjVmOWZiZTJjZjA0ZDAxMDAzN2E0ZDc5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.m-Dda75kQLr1zEk-xDKJpRLAi1G0bXVTq8sMt1LE02c"),
        )
        return chain.proceed(newRequestBuilder.build())
    }
}
