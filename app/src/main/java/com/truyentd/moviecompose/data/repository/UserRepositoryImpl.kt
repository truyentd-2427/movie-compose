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
        // TODO Replace token generate from themoviedb dashboard if this token expired
        localDataSource.saveAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MjdiNWFhOGE0NmFmMGE5OGUyYjM5OTIxMThmNDYzYiIsIm5iZiI6MTYwNDMwNDQyOC42MTUsInN1YiI6IjVmOWZiZTJjZjA0ZDAxMDAzN2E0ZDc5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.l2WF5lomyzk-7VVsbwMhVa5nAUaDXmAbdSV2E_2tRR8")
        localDataSource.saveHasLoggedIn(true)
    }

    override fun hasLoggedIn(): Boolean {
        return localDataSource.hasLoggedIn()
    }

    override fun logout() {
        localDataSource.logout()
    }
}
