package com.truyentd.moviecompose.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class BaseDestination

@Serializable
sealed class AppRoute : BaseDestination() {
    // AuthGraph
    @Serializable
    data object AuthGraph : AppRoute()

    @Serializable
    data object Login : AppRoute()

    // TopGraph
    @Serializable
    data object TopGraph : AppRoute()

    @Serializable
    data object Top : AppRoute()

    @Serializable
    data object Home : AppRoute()

    @Serializable
    data object Search : AppRoute()

    @Serializable
    data object Bookmark : AppRoute()

    @Serializable
    data class MovieDetail(val movieId: Int = -1) : AppRoute()
}

sealed class NavigationType : BaseDestination() {
    // Navigation Actions
    data class PopBackStack<T : AppRoute>(
        val route: T,
        val inclusive: Boolean,
        val saveState: Boolean = false,
        val results: HashMap<String, Any>? = null
    ) : AppRoute()

    data class NavigateUp(val results: HashMap<String, Any>? = null) : AppRoute()
}
