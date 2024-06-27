package com.truyentd.moviecompose.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class AppTypography(
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val heading1: TextStyle,
    val heading2: TextStyle,
    val heading3: TextStyle,
    val heading4: TextStyle,
    val heading5: TextStyle,
    val heading6: TextStyle,
)

val DefaultTypography = AppTypography(
    body1 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W400),
    body2 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400),
    body3 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W400),
    heading1 = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.W700),
    heading2 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.W700),
    heading3 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W700),
    heading4 = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W500),
    heading5 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500),
    heading6 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.W500),
)

val LightTypography = AppTypography(
    body1 = DefaultTypography.body1.copy(color = AppColors.Black),
    body2 = DefaultTypography.body2.copy(color = AppColors.Black),
    body3 = DefaultTypography.body3.copy(color = AppColors.Black),
    heading1 = DefaultTypography.heading1.copy(color = AppColors.PrimaryLight),
    heading2 = DefaultTypography.heading2.copy(color = AppColors.PrimaryLight),
    heading3 = DefaultTypography.heading3.copy(color = AppColors.PrimaryLight),
    heading4 = DefaultTypography.heading4.copy(color = AppColors.PrimaryLight),
    heading5 = DefaultTypography.heading5.copy(color = AppColors.PrimaryLight),
    heading6 = DefaultTypography.heading6.copy(color = AppColors.PrimaryLight),
)

val DarkTypography = AppTypography(
    body1 = DefaultTypography.body1.copy(color = AppColors.White),
    body2 = DefaultTypography.body2.copy(color = AppColors.White),
    body3 = DefaultTypography.body3.copy(color = AppColors.White),
    heading1 = DefaultTypography.heading1.copy(color = AppColors.PrimaryDark),
    heading2 = DefaultTypography.heading2.copy(color = AppColors.PrimaryDark),
    heading3 = DefaultTypography.heading3.copy(color = AppColors.PrimaryDark),
    heading4 = DefaultTypography.heading4.copy(color = AppColors.PrimaryDark),
    heading5 = DefaultTypography.heading5.copy(color = AppColors.PrimaryDark),
    heading6 = DefaultTypography.heading6.copy(color = AppColors.PrimaryDark),
)

val LocalAppTypography = staticCompositionLocalOf { LightTypography }

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
