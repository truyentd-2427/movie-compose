package com.truyentd.moviecompose.presentation.screens.main

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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.top.TopDestination
import com.truyentd.moviecompose.navigation.top.TopNavHost
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview
@Composable
fun TopScreenPreview() {
    TopScreen({}, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopScreen(onMovieClick: (MovieData) -> Unit, onFavoriteClick: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        containerColor = AppColors.White,
        bottomBar = {
            val bottomNavScreens = listOf(
                TopDestination.Home,
                TopDestination.Search,
                TopDestination.Favorite,
            )
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            BottomNavigation(
                modifier = Modifier.navigationBarsPadding(),
                backgroundColor = Color.White,
                elevation = 12.dp,
            ) {
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
        TopNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onMovieClick = onMovieClick,
            onFavoriteClick = onFavoriteClick,
        )
    }
}
