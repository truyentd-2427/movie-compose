package com.truyentd.moviecompose.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.truyentd.moviecompose.navigation.auth.authNavGraph
import com.truyentd.moviecompose.navigation.movie.movieNavGraph
import com.truyentd.moviecompose.navigation.top.topNavGraph

sealed class AppNavGraph(route: String) : BaseDestination(route) {
    object Root : AppNavGraph(route = "root-graph")

    object Auth : AppNavGraph(route = "auth-graph")

    object Top : AppNavGraph(route = "top-graph")

    object Movie : AppNavGraph(route = "movie-graph")
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination: BaseDestination) {
    NavHost(
        modifier = Modifier.background(MaterialTheme.colorScheme.onPrimary),
        navController = navController,
        route = AppNavGraph.Root.route,
        startDestination = startDestination.route
    ) {
        authNavGraph(navController)
        topNavGraph(navController)
        movieNavGraph(navController)
    }
}
