package com.truyentd.moviecompose.data.repository.source.local.api.helper

import com.truyentd.moviecompose.data.repository.source.local.error.PersistenceError
import com.truyentd.moviecompose.domain.error.ErrorEntity
import com.truyentd.moviecompose.domain.error.ErrorMapper

object PersistenceErrorMapper : ErrorMapper {
    override fun map(throwable: Throwable): ErrorEntity {
        // TODO: handle other error if needed
        return PersistenceError.SharedPrefError(throwable)
    }
}
