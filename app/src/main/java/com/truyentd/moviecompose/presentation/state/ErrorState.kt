package com.truyentd.moviecompose.presentation.state

data class ErrorState(
    val throwable: Throwable? = null,
    val shouldShowDialog: Boolean = false,
)
