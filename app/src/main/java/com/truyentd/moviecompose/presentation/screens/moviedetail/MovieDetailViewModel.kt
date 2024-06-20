package com.truyentd.moviecompose.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieCreditsUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieDetailUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
) : BaseViewModel() {
    private val movieId: String = checkNotNull(savedStateHandle["movieId"])

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
        getMovieCredits()
    }

    private fun getMovieDetail() {
        launchUseCase(
            getMovieDetailUseCase,
            GetMovieDetailUseCase.Input(movieId = movieId.toInt()),
        ) { movie ->
            _uiState.update { it.copy(movie = movie) }
        }
    }

    private fun getMovieCredits() {
        launchUseCase(
            getMovieCreditsUseCase,
            GetMovieCreditsUseCase.Input(movieId = movieId.toInt()),
        ) { casts ->
            _uiState.update { it.copy(casts = casts) }
        }
    }
}
