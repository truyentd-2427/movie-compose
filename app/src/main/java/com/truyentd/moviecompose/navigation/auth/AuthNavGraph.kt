package com.truyentd.moviecompose.navigation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.truyentd.moviecompose.navigation.AppNavGraph
import com.truyentd.moviecompose.navigation.composable
import com.truyentd.moviecompose.presentation.screens.login.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppNavGraph.Auth.route,
        startDestination = AuthDestination.Login.route
    ) {
        composable(destination = AuthDestination.Login) {
            LoginScreen()
        }
    }
}
