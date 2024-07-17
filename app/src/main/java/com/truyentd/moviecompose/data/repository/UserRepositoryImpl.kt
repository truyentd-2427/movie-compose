package com.truyentd.moviecompose.data.repository

import com.truyentd.moviecompose.data.repository.source.local.UserLocalDataSource
import com.truyentd.moviecompose.domain.repository.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: UserLocalDataSource,
) : UserRepository {
    override suspend fun login() {
        delay(1500)
        localDataSource.saveAccessToken("PLACE_ACCESS_TOKEN_HERE")
        localDataSource.saveHasLoggedIn(true)
    }

    override fun hasLoggedIn(): Boolean {
        return localDataSource.hasLoggedIn()
    }

    override fun logout() {
        localDataSource.logout()
    }
}
