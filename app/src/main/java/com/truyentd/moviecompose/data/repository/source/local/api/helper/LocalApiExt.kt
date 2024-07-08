package com.truyentd.moviecompose.data.repository.source.local.api.helper

import com.truyentd.moviecompose.data.repository.source.local.api.SharedPrefApi

inline fun <R> SharedPrefApi.execute(block: SharedPrefApi.() -> R): R {
    try {
        return block()
    } catch (throwable: Throwable) {
        throw PersistenceErrorMapper.map(throwable)
    }
}
