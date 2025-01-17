package com.truyentd.moviecompose.presentation.screens.top

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.presentation.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.navigation.top.TopNestedNavHost
import com.truyentd.moviecompose.presentation.screens.AppViewModel
import com.truyentd.moviecompose.presentation.screens.LocalAppViewModel
import com.truyentd.moviecompose.presentation.screens.top.components.AppBottomNavigation
import com.truyentd.moviecompose.presentation.theme.AppColors
import com.truyentd.moviecompose.shared.extension.collectAsEffect

@Preview
@Composable
fun TopScreenPreview() {
    TopScreen {}
}

@Composable
fun TopScreen(
    appViewModel: AppViewModel = LocalAppViewModel.current,
    navigator: (BaseDestination) -> Unit,
) {
    val navController = rememberNavController()
    val uiState = rememberTopUiState(
        snackbarHostState = remember { SnackbarHostState() },
        snackbarScope = rememberCoroutineScope()
    )
    appViewModel.snackbarMessage.collectAsEffect { snackbarData ->
        if (snackbarData != null) {
            uiState.showSnackbar(snackbarData)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(uiState.snackbarHostState) },
        containerColor = AppColors.White,
        bottomBar = {
            AppBottomNavigation(navController)
        }
    ) { innerPadding ->
        TopNestedNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navigator = navigator,
        )
    }
}
