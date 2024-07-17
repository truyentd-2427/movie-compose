package com.truyentd.moviecompose.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(
    primary = AppColors.PrimaryLight,
    onPrimary = AppColors.OnPrimaryLight,
    secondary = AppColors.SecondaryLight,
    onSecondary = AppColors.OnSecondaryLight,
    tertiary = AppColors.TertiaryLight,
    onTertiary = AppColors.OnTertiaryLight,
)

val DarkColorScheme = darkColorScheme(
    primary = AppColors.PrimaryDark,
    onPrimary = AppColors.OnPrimaryDark,
    secondary = AppColors.SecondaryDark,
    onSecondary = AppColors.OnSecondaryDark,
    tertiary = AppColors.TertiaryDark,
    onTertiary = AppColors.OnTertiaryDark,
)

object AppColors {
    // Light color scheme
    val PrimaryLight = Color(0xFF110E47)
    val OnPrimaryLight = Color(0xFFFFFFFF)
    val SecondaryLight = Color(0xFF5D5C71)
    val OnSecondaryLight = Color(0xFFFFFFFF)
    val TertiaryLight = Color(0xFF7A5368)
    val OnTertiaryLight = Color(0xFFFFFFFF)

    // Dark color scheme
    val PrimaryDark = Color(0xFFC2C1FF)
    val OnPrimaryDark = Color(0xFF1A2130)
    val SecondaryDark = Color(0xFFC7C4DD)
    val OnSecondaryDark = Color(0xFF2F2F42)
    val TertiaryDark = Color(0xFFEAB9D2)
    val OnTertiaryDark = Color(0xFF472639)

    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Violet = Color(0xFF110E47)
    val DustyGray = Color(0xFF9C9C9C)
    val LightningYellow = Color(0xFFFFC319)
    val Fog = Color(0xFFDBE3FF)
    val Portage = Color(0xFF88A4E8)
    val WhiteLilac = Color(0xFFF0EFFA)
    val Mischka = Color(0xFFE5E4EA)
    val FlamePea = Color(0xFFDD5746)
}
