package com.truyentd.moviecompose.data.repository.source.remote.error

import com.truyentd.moviecompose.data.repository.source.remote.api.response.BaseErrorResponse
import com.truyentd.moviecompose.domain.error.ErrorEntity

sealed class ApiError : ErrorEntity() {

    data class HttpError(
        override val originalThrowable: Throwable,
        val errorResponse: BaseErrorResponse?,
    ) : ApiError()

    data class ServerError(
        override val originalThrowable: Throwable,
        val errorResponse: BaseErrorResponse?,
    ) : ApiError()

    data class NetworkError(override val originalThrowable: Throwable) : ApiError()

    data class UnexpectedError(override val originalThrowable: Throwable) : ApiError()
}
