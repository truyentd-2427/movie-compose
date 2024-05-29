package com.truyentd.moviecompose.domain.error

interface ErrorMapper {
    fun map(throwable: Throwable): ErrorEntity
}
