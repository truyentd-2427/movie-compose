package com.truyentd.moviecompose.presentation.screens.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.presentation.navigation.BottomNavScreen
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen
import com.truyentd.moviecompose.presentation.screens.profile.ProfileScreen
import com.truyentd.moviecompose.presentation.screens.setting.FavoriteScreen
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        containerColor = AppColors.White,
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.navigationBarsPadding(),
                backgroundColor = Color.White,
                elevation = 12.dp,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val bottomNavScreens = listOf(
                    BottomNavScreen.Home,
                    BottomNavScreen.Favorite,
                    BottomNavScreen.Setting
                )
                bottomNavScreens.forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = if (selected) screen.selectedIcon else screen.unselectedIcon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
        ) {
            composable(route = BottomNavScreen.Home.route) {
                HomeScreen()
            }
            composable(route = BottomNavScreen.Favorite.route) {
                ProfileScreen()
            }
            composable(route = BottomNavScreen.Setting.route) {
                FavoriteScreen()
            }
        }
    }
}
