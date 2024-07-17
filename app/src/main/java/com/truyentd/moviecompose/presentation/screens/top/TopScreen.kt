package com.truyentd.moviecompose.presentation.screens.top

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.navigation.top.TopNestedNavHost
import com.truyentd.moviecompose.presentation.screens.top.components.AppBottomNavigation
import com.truyentd.moviecompose.presentation.theme.AppColors

@Preview
@Composable
fun TopScreenPreview() {
    TopScreen {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopScreen(navigator: (BaseDestination) -> Unit) {
    val navController = rememberNavController()
    Scaffold(
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
