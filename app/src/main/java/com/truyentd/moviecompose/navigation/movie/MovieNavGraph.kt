package com.truyentd.moviecompose.navigation.movie

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(300)) },
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(300)) },
            popEnterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(300)) },
            popExitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(300)) }
        ) {
            MovieDetailScreen()
        }
    }
}
