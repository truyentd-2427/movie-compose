package com.truyentd.moviecompose.data.repository.source.remote.api.helper

object ApiConfig {
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer"

    fun baseUrl(): String {
        return ""
    }

    fun getBearerToken(idToken: String?): String {
        return "$BEARER $idToken"
    }
}
