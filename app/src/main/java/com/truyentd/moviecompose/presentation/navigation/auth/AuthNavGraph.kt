package com.truyentd.moviecompose.presentation.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import com.truyentd.moviecompose.presentation.screens.login.LoginScreen
import com.truyentd.moviecompose.shared.extension.composableX
import com.truyentd.moviecompose.shared.extension.navigateX

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation<AppRoute.AuthGraph>(
        startDestination = AppRoute.Login
    ) {
        composableX<AppRoute.Login> {
            LoginScreen(
                navigator = { destination ->
                    when (destination) {
                        is AppRoute.TopGraph -> navController.navigateX(destination) {
                            popUpTo(AppRoute.AuthGraph) {
                                inclusive = true
                            }
                        }

                        else -> navController.navigateX(destination)
                    }
                },
            )
        }
    }
}
