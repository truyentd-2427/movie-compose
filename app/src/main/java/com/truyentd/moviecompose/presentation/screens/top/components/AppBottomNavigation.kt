package com.truyentd.moviecompose.presentation.screens.top.components

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.truyentd.moviecompose.presentation.navigation.top.topRoutes

@Composable
fun AppBottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(
        modifier = Modifier.navigationBarsPadding()
    ) {
        topRoutes.forEach { stack ->
            val selected =
                currentDestination?.hierarchy?.any { it.hasRoute(stack.route::class) } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = if (selected) stack.selectedIcon else stack.unselectedIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                label = { Text(text = stack.label) },
                selected = selected,
                onClick = {
                    navController.navigate(stack.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
    }
}
