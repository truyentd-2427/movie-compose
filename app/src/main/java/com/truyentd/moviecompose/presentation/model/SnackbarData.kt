package com.truyentd.moviecompose.presentation.model

import androidx.compose.material3.SnackbarDuration

data class SnackbarData(
    val message: String,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    private val id: Long = System.currentTimeMillis()
)
