package com.truyentd.moviecompose.navigation.movie

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.presentation.screens.moviedetail.MovieDetailScreen

fun NavGraphBuilder.movieNavGraph(navController: NavHostController) {
    navigation(
        route = AppNavGraph.Movie.route,
        startDestination = MovieDestination.MovieDetail.route
    ) {
        composable(
            route = MovieDestination.MovieDetail.route,
            arguments = listOf(navArgument("movieId") { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://moviecompose.com/movie-detail/{movieId}"
            })
        ) {
            MovieDetailScreen()
        }
    }
}
