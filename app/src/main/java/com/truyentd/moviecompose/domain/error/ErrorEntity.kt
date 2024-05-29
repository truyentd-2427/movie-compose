package com.truyentd.moviecompose.domain.error

abstract class ErrorEntity : Throwable() {
    abstract val originalThrowable: Throwable
}
