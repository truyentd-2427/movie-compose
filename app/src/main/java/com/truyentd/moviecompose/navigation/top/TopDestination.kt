package com.truyentd.moviecompose.navigation.top

import androidx.annotation.DrawableRes
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.navigation.AppDestination

sealed class TopDestination(
    route: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) : AppDestination(route) {
    object Home : TopDestination(
        route = "home",
        unselectedIcon = R.drawable.ic_film_unselected,
        selectedIcon = R.drawable.ic_film_selected,
    )

    object Setting : TopDestination(
        route = "setting",
        unselectedIcon = R.drawable.ic_ticket_unselected,
        selectedIcon = R.drawable.ic_ticket_selected,
    )

    object Favorite : TopDestination(
        route = "profile",
        unselectedIcon = R.drawable.ic_bookmark_unselected,
        selectedIcon = R.drawable.ic_bookmark_selected,
    )
}
