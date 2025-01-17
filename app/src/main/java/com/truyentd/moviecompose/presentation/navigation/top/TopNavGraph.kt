package com.truyentd.moviecompose.presentation.navigation.top

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import com.truyentd.moviecompose.presentation.screens.moviedetail.MovieDetailScreen
import com.truyentd.moviecompose.presentation.screens.top.TopScreen
import com.truyentd.moviecompose.shared.extension.composableX
import com.truyentd.moviecompose.shared.extension.navigateX

fun NavGraphBuilder.topNavGraph(navController: NavHostController) {
    navigation<AppRoute.TopGraph>(startDestination = AppRoute.Top) {
        composableX<AppRoute.Top> {
            TopScreen(
                navigator = { destination ->
                    when (destination) {
                        is AppRoute.AuthGraph -> navController.navigateX(destination) {
                            popUpTo(AppRoute.TopGraph) {
                                inclusive = true
                            }
                        }

                        else -> navController.navigateX(destination)
                    }
                },
            )
        }
        composableX<AppRoute.MovieDetail> {
            MovieDetailScreen(navigator = navController::navigateX)
        }
    }
}
