package com.truyentd.moviecompose.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.truyentd.moviecompose.presentation.navigation.auth.authNavGraph
import com.truyentd.moviecompose.presentation.navigation.top.topNavGraph

@Composable
fun AppNavHost(navController: NavHostController, startDestination: BaseDestination) {
    NavHost(
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),
        navController = navController,
        startDestination = startDestination
    ) {
        authNavGraph(navController)
        topNavGraph(navController)
    }
}
