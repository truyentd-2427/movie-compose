package com.truyentd.moviecompose.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

@Composable
fun MovieComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val appColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val appTypography = if (darkTheme) DarkTypography else LightTypography

    CompositionLocalProvider(LocalAppTypography provides appTypography) {
        MaterialTheme(
            colorScheme = appColorScheme,
            typography = Typography,
            content = content
        )
    }
}

// Usage: AppTheme.typography.body1
object AppTheme {
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}
