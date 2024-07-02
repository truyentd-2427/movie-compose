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
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.navigation.top.TopDestination
import com.truyentd.moviecompose.navigation.top.TopNavHost
import com.truyentd.moviecompose.presentation.screens.main.components.AppBottomNavigation
import com.truyentd.moviecompose.presentation.theme.AppColors

@Preview
@Composable
fun TopScreenPreview() {
    TopScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopScreen(navigator: ((BaseDestination) -> Unit)? = null) {
    val navController = rememberNavController()
    Scaffold(
        containerColor = AppColors.White,
        bottomBar = {
            AppBottomNavigation(navController)
        }
    ) { innerPadding ->
        TopNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navigator = navigator,
        )
    }
}
