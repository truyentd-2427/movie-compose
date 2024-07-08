package com.truyentd.moviecompose.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.screens.login.LoginScreen
import com.truyentd.moviecompose.shared.extension.composable
import com.truyentd.moviecompose.shared.extension.navigate

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppNavGraph.Auth.route,
        startDestination = AuthDestination.Login.route
    ) {
        composable(destination = AuthDestination.Login) {
            LoginScreen(
                navigator = { destination ->
                    if (destination is AppNavGraph.Top) {
                        navController.goToTopScreen(destination)
                    } else {
                        navController.navigate(destination)
                    }
                },
            )
        }
    }
}

private fun NavHostController.goToTopScreen(destination: BaseDestination) {
    navigate(destination) {
        popUpTo(AppNavGraph.Auth.route) {
            inclusive = true
        }
    }
}
