package com.truyentd.moviecompose.data.repository.source.remote.api.middleware

import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi
import com.truyentd.moviecompose.data.repository.source.local.api.pref.PREF_ACCESS_TOKEN
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.ApiConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPrefApi: SharedPrefApi) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequestBuilder = originalRequest.newBuilder()
        newRequestBuilder.header(
            ApiConfig.AUTHORIZATION,
            ApiConfig.getBearerToken(sharedPrefApi.get(PREF_ACCESS_TOKEN, String::class.java)),
        )
        return chain.proceed(newRequestBuilder.build())
    }
}
