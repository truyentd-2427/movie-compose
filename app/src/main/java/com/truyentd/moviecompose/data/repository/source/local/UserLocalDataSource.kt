package com.truyentd.moviecompose.data.repository.source.local

import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi
import com.truyentd.moviecompose.data.repository.source.local.api.helper.execute
import com.truyentd.moviecompose.data.repository.source.local.api.pref.PREF_ACCESS_TOKEN
import com.truyentd.moviecompose.data.repository.source.local.api.pref.PREF_HAS_LOGGED_IN
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val sharedPrefApi: SharedPrefApi,
) {
    fun saveHasLoggedIn(hasLoggedIn: Boolean) {
        sharedPrefApi.execute { put(PREF_HAS_LOGGED_IN, hasLoggedIn) }
    }

    fun hasLoggedIn(): Boolean {
        return sharedPrefApi.execute { get(PREF_HAS_LOGGED_IN, Boolean::class.java) } ?: false
    }

    fun logout() {
        sharedPrefApi.execute { removeKey(PREF_ACCESS_TOKEN) }
        sharedPrefApi.execute { removeKey(PREF_HAS_LOGGED_IN) }
    }

    fun saveAccessToken(accessToken: String) {
        sharedPrefApi.execute { put(PREF_ACCESS_TOKEN, accessToken) }
    }
}
