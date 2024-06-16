package com.truyentd.moviecompose.data.repository.source.remote.api.helper

object ApiConfig {
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer"

    fun baseUrl(): String {
        return "https://api.themoviedb.org"
    }

    fun getBearerToken(idToken: String?): String {
        return "$BEARER $idToken"
    }
}
