package com.truyentd.moviecompose.navigation.movie

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.truyentd.moviecompose.navigation.BaseDestination

sealed class MovieDestination {
    object MovieDetail : BaseDestination("movie/{movieId}") {
        override val arguments = listOf(
            navArgument("movieId") { type = NavType.StringType }
        )

        fun createRoute(id: String) = apply {
            destination = "movie/$id"
        }
    }
}
