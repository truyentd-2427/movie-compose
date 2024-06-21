package com.truyentd.moviecompose.navigation.movie

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.composable
import com.truyentd.moviecompose.presentation.screens.moviedetail.MovieDetailScreen

fun NavGraphBuilder.movieNavGraph(navController: NavHostController) {
    navigation(
        route = AppNavGraph.Movie.route,
        startDestination = MovieDestination.MovieDetail.route
    ) {
        composable(
            destination = MovieDestination.MovieDetail,
        ) {
            MovieDetailScreen()
        }
    }
}
