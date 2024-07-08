package com.truyentd.moviecompose.domain.repository

interface UserRepository {
    suspend fun login()

    fun hasLoggedIn(): Boolean

    fun logout()
}
