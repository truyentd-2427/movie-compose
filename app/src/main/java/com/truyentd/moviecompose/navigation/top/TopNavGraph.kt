package com.truyentd.moviecompose.navigation.top

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.presentation.screens.top.TopScreen
import com.truyentd.moviecompose.shared.extension.composable
import com.truyentd.moviecompose.shared.extension.navigate

fun NavGraphBuilder.topNavGraph(navController: NavHostController) {
    composable(destination = AppNavGraph.Top) {
        TopScreen(
            navigator = { destination ->
                if (destination is AppNavGraph.Auth) {
                    navController.goToLoginScreen(destination)
                } else {
                    navController.navigate(destination)
                }
            },
        )
    }
}

private fun NavHostController.goToLoginScreen(destination: AppNavGraph.Auth) {
    navigate(destination) {
        popUpTo(AppNavGraph.Top.route) {
            inclusive = true
        }
    }
}
