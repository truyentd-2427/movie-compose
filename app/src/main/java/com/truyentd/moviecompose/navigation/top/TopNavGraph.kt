package com.truyentd.moviecompose.navigation.top

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.movie.MovieDestination
import com.truyentd.moviecompose.presentation.screens.favorite.FavoriteScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.main.TopScreen
import com.truyentd.moviecompose.presentation.screens.profile.ProfileScreen

fun NavGraphBuilder.topNavGraph(navController: NavHostController) {
    composable(AppNavGraph.Top.route) {
        TopScreen(
            onMovieClick = { movie ->
                navController.navigate("movie-detail/${movie.id}")
            },
            onFavoriteClick = {
                navController.navigate(MovieDestination.MovieDetail.route)
            },
        )
    }
}


@Composable
fun TopNavHost(
    navController: NavHostController,
    modifier: Modifier,
    onMovieClick: (MovieData) -> Unit,
    onFavoriteClick: () -> Unit,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = TopDestination.Home.route
    ) {
        composable(
            route = TopDestination.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            HomeScreen(onMovieClick = onMovieClick)
        }
        composable(
            route = TopDestination.Favorite.route,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://moviecompose.com/favorite"
            }),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            FavoriteScreen {
                onFavoriteClick.invoke()
            }
        }
        composable(
            route = TopDestination.Setting.route, enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            ProfileScreen()
        }
    }
}
