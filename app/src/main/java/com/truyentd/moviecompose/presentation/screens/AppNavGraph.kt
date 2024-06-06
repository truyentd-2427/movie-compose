package com.truyentd.moviecompose.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.truyentd.moviecompose.presentation.components.bottombar.BottomBarScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.profile.ProfileScreen
import com.truyentd.moviecompose.presentation.screens.search.SettingScreen

@Composable
fun BottomBarNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen("Home Screen")
        }

        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen("Profile Screen")
        }
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen("Setting Screen")
        }
    }
}
