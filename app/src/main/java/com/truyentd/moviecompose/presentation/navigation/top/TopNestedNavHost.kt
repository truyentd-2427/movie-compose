package com.truyentd.moviecompose.presentation.navigation.top

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import com.truyentd.moviecompose.presentation.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.screens.bookmark.BookmarkScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.search.SearchScreen
import com.truyentd.moviecompose.shared.extension.composableX

@Composable
fun TopNestedNavHost(
    navController: NavHostController,
    modifier: Modifier,
    navigator: (BaseDestination) -> Unit,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = AppRoute.Home
    ) {
        composableX<AppRoute.Home>(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            HomeScreen(navigator = navigator)
        }
        composableX<AppRoute.Search>(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            SearchScreen(navigator = navigator)
        }
        composableX<AppRoute.Bookmark>(
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            BookmarkScreen(navigator = navigator)
        }
    }
}
