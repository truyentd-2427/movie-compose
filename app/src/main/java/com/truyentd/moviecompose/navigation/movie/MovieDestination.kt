package com.truyentd.moviecompose.navigation.movie

sealed class MovieDestination(val route: String) {
    object MovieDetail: MovieDestination("movie-detail/{movieId}")
}
