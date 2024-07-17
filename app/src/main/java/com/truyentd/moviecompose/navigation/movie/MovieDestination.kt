package com.truyentd.moviecompose.navigation.movie

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.shared.constant.KEY_MOVIE_ID

sealed class MovieDestination {
    object MovieDetail : BaseDestination("movie/{$KEY_MOVIE_ID}") {
        override val arguments = listOf(
            navArgument(KEY_MOVIE_ID) { type = NavType.StringType }
        )

        fun createRoute(id: String) = apply {
            destination = "movie/$id"
        }
    }
}
