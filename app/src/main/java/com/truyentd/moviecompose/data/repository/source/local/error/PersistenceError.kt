package com.truyentd.moviecompose.data.repository.source.local.error

import com.truyentd.moviecompose.domain.error.ErrorEntity

sealed class PersistenceError : ErrorEntity() {
    data class SharedPrefError(override val originalThrowable: Throwable) : PersistenceError()
}
