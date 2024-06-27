package com.truyentd.moviecompose.navigation

import androidx.compose.runtime.Composable
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
fun AppNavHost(navController: NavHostController, hasLoggedIn: Boolean) {
    NavHost(
        navController = navController,
        route = AppNavGraph.Root.route,
        startDestination = if (hasLoggedIn) AppNavGraph.Top.route else AppNavGraph.Auth.route
    ) {
        authNavGraph(navController)
        topNavGraph(navController)
        movieNavGraph(navController)
    }
}
