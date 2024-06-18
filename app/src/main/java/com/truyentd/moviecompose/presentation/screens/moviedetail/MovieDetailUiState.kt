package com.truyentd.moviecompose.presentation.screens.moviedetail

import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.data.model.MovieData

data class MovieDetailUiState(
    val movie: MovieData? = null,
    val casts: List<CastData> = emptyList(),
)
