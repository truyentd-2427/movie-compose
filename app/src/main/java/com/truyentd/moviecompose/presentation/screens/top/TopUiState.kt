package com.truyentd.moviecompose.presentation.screens.top

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.truyentd.moviecompose.presentation.model.SnackbarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class TopUiState(
    val snackbarHostState: SnackbarHostState,
    val snackbarScope: CoroutineScope
) {
    fun showSnackbar(snackbarData: SnackbarData) {
        snackbarScope.launch {
            snackbarHostState.showSnackbar(
                message = snackbarData.message,
                duration = snackbarData.duration
            )
        }
    }
}

@Composable
fun rememberTopUiState(
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope
) = remember(snackbarHostState, snackbarScope) {
    TopUiState(
        snackbarHostState = snackbarHostState,
        snackbarScope = snackbarScope
    )
}