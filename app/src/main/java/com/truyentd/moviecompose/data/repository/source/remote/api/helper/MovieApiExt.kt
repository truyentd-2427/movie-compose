package com.truyentd.moviecompose.data.repository.source.remote.api.helper

import com.truyentd.moviecompose.data.repository.source.remote.api.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.NoneAuthApi
import com.truyentd.moviecompose.data.repository.source.remote.error.ApiErrorMapper

inline fun <R> AuthApi.execute(block: AuthApi.() -> R): R {
    try {
        return block()
    } catch (throwable: Throwable) {
        throw ApiErrorMapper.map(throwable)
    }
}

inline fun <R> NoneAuthApi.execute(block: NoneAuthApi.() -> R): R {
    try {
        return block()
    } catch (throwable: Throwable) {
        throw ApiErrorMapper.map(throwable)
    }
}
