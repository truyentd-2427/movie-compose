package com.truyentd.moviecompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.truyentd.moviecompose.navigation.auth.authNavGraph
import com.truyentd.moviecompose.navigation.movie.movieNavGraph
import com.truyentd.moviecompose.navigation.top.topNavGraph
import com.truyentd.moviecompose.presentation.screens.moviedetail.MovieDetailScreen

sealed class AppNavGraph(val route: String) {
    object Root : AppNavGraph(route = "root-graph")

    object Auth : AppNavGraph(route = "auth-graph")

    object Top : AppNavGraph(route = "top-graph")

    object Movie : AppNavGraph(route = "movie-graph")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppNavGraph.Root.route,
        startDestination = AppNavGraph.Top.route
    ) {
        authNavGraph(navController)
        topNavGraph(navController)
        movieNavGraph(navController)
    }
}
