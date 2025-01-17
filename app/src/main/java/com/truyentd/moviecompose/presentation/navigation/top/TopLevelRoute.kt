package com.truyentd.moviecompose.presentation.navigation.top

import androidx.annotation.DrawableRes
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class TopLevelRoute<T>(
    val route: T,
    val label: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
) {
    @Serializable
    data object Home : TopLevelRoute<AppRoute.Home>(
        route = AppRoute.Home,
        label = "Home",
        unselectedIcon = R.drawable.ic_film_unselected,
        selectedIcon = R.drawable.ic_film_selected,
    )

    @Serializable
    data object Search : TopLevelRoute<AppRoute.Search>(
        route = AppRoute.Search,
        label = "Search",
        unselectedIcon = R.drawable.ic_ticket_unselected,
        selectedIcon = R.drawable.ic_ticket_selected,
    )

    @Serializable
    data object Bookmark : TopLevelRoute<AppRoute.Bookmark>(
        route = AppRoute.Bookmark,
        label = "Bookmark",
        unselectedIcon = R.drawable.ic_bookmark_unselected,
        selectedIcon = R.drawable.ic_bookmark_selected,
    )
}

val topRoutes = listOf(
    TopLevelRoute.Home,
    TopLevelRoute.Search,
    TopLevelRoute.Bookmark,
)