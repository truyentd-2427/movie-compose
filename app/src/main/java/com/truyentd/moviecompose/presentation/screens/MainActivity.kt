package com.truyentd.moviecompose.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.truyentd.moviecompose.presentation.screens.main.MainScreen
import com.truyentd.moviecompose.ui.theme.MovieComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MovieComposeTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainActivityPreview() {
    MainScreen()
}
