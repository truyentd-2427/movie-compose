package com.truyentd.moviecompose.presentation.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
fun MovieComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current as ComponentActivity

    // Update status bar color base on app theme
    LaunchedEffect(darkTheme) {
        context.enableEdgeToEdge(
            statusBarStyle = if (darkTheme) {
                SystemBarStyle.dark(
                    AppColors.OnPrimaryDark.toArgb()
                )
            } else {
                SystemBarStyle.auto(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT,
                )
            }
        )
    }

    val appColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val appTypography = if (darkTheme) DarkTypography else LightTypography
    CompositionLocalProvider(LocalAppTypography provides appTypography) {
        MaterialTheme(
            colorScheme = appColorScheme,
            typography = Typography(bodyLarge = BaseTypography.body3),
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
