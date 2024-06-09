package com.truyentd.moviecompose.presentation.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.truyentd.moviecompose.presentation.screens.home.HomeScreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile Screen")
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
