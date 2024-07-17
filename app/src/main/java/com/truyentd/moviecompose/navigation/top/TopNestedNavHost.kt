package com.truyentd.moviecompose.navigation.top

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.screens.bookmark.BookmarkScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.search.SearchScreen
import com.truyentd.moviecompose.shared.extension.composable

@Composable
fun TopNestedNavHost(
    navController: NavHostController,
    modifier: Modifier,
    navigator: (BaseDestination) -> Unit,
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
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            HomeScreen(navigator = navigator)
        }
        composable(
            destination = TopDestination.Search,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            SearchScreen(navigator = navigator)
        }
        composable(
            destination = TopDestination.Bookmark,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            BookmarkScreen(navigator = navigator)
        }
    }
}
