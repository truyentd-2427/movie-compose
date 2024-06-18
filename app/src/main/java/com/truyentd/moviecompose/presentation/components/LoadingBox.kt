package com.truyentd.moviecompose.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        content()
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
