package com.truyentd.moviecompose.data.repository.source.remote.error

import com.google.gson.GsonBuilder
import com.truyentd.moviecompose.data.repository.source.remote.api.response.BaseErrorResponse
import com.truyentd.moviecompose.domain.error.ErrorEntity
import com.truyentd.moviecompose.domain.error.ErrorMapper
import retrofit2.HttpException
import java.io.IOException

object ApiErrorMapper : ErrorMapper {
    override fun map(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is HttpException -> errorEntityFromHttpException(throwable)
            is IOException -> ApiError.NetworkError(throwable)
            else -> ApiError.UnexpectedError(throwable)
        }
    }

    private fun errorEntityFromHttpException(throwable: HttpException): ErrorEntity {
        val errorBody = throwable.response()?.errorBody()
        if (errorBody != null) {
            val errorResponse = deserializeServerError(errorBody.string())
                ?: return ApiError.UnexpectedError(throwable)
            return if (throwable.code() > 500) {
                ApiError.ServerError(throwable, errorResponse)
            } else {
                ApiError.HttpError(throwable, errorResponse)
            }
        }
        return ApiError.UnexpectedError(throwable)
    }

    private fun deserializeServerError(errorString: String): BaseErrorResponse? {
        val gson = GsonBuilder().create()
        return try {
            gson.fromJson(errorString, BaseErrorResponse::class.java)
        } catch (ignored: Exception) {
            null
        }
    }
}