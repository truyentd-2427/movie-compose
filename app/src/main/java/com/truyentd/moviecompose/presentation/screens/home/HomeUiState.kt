package com.truyentd.moviecompose.presentation.screens.home

import com.truyentd.moviecompose.data.model.MovieData

data class HomeUiState(
    val nowPlayingMovies: List<MovieData> = emptyList(),
    val popularMovies: List<MovieData> = emptyList(),
)
