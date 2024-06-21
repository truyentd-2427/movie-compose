package com.truyentd.moviecompose.navigation.top

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.navigation.composable
import com.truyentd.moviecompose.navigation.navigate
import com.truyentd.moviecompose.presentation.screens.favorite.FavoriteScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.main.TopScreen
import com.truyentd.moviecompose.presentation.screens.search.SearchScreen

fun NavGraphBuilder.topNavGraph(navController: NavHostController) {
    composable(route = AppNavGraph.Top.route) {
        TopScreen(
            navigator = { destination ->
                navController.navigate(destination)
            },
        )
    }
}

@Composable
fun TopNavHost(
    navController: NavHostController,
    modifier: Modifier,
    navigator: ((BaseDestination) -> Unit)? = null,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = TopDestination.Home.route
    ) {
        composable(
            destination = TopDestination.Home,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            HomeScreen(navigator = navigator)
        }
        composable(
            destination = TopDestination.Search,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            SearchScreen()
        }
        composable(
            destination = TopDestination.Favorite,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            FavoriteScreen()
        }
    }
}
