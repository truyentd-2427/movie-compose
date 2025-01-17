package com.truyentd.moviecompose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.presentation.navigation.AppNavHost
import com.truyentd.moviecompose.presentation.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(LocalAppViewModel provides appViewModel) {
                val isDarkTheme by appViewModel.isDarkTheme.collectAsStateWithLifecycle()
                MovieComposeTheme(darkTheme = isDarkTheme) {
                    AppNavHost(
                        navController = rememberNavController(),
                        startDestination = appViewModel.startDestination,
                    )
                }
            }
        }
    }
}
