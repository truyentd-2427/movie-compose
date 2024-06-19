package com.truyentd.moviecompose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.truyentd.moviecompose.navigation.AppNavHost
import com.truyentd.moviecompose.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieComposeTheme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}
