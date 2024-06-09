package com.truyentd.moviecompose.presentation.navigation

import androidx.annotation.DrawableRes
import com.truyentd.moviecompose.R

abstract class AppScreen(val route: String) {
}

sealed class BottomNavScreen(
    route: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) : AppScreen(route) {
    object Home : BottomNavScreen(
        route = "home",
        unselectedIcon = R.drawable.ic_film_unselected,
        selectedIcon = R.drawable.ic_film_selected,
    )

    object Setting : BottomNavScreen(
        route = "setting",
        unselectedIcon = R.drawable.ic_ticket_unselected,
        selectedIcon = R.drawable.ic_ticket_selected,
    )

    object Favorite : BottomNavScreen(
        route = "profile",
        unselectedIcon = R.drawable.ic_bookmark_unselected,
        selectedIcon = R.drawable.ic_bookmark_selected,
    )
}
