package com.truyentd.moviecompose.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.truyentd.moviecompose.domain.usecase.movie.BookmarkMovieUseCase
import com.truyentd.moviecompose.domain.usecase.movie.DeleteBookmarkMovieUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieCreditsUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieDetailUseCase
import com.truyentd.moviecompose.domain.usecase.movie.HasBookmarkMovieUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.navigation.AppRoute
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
    private val hasBookmarkMovieUseCase: HasBookmarkMovieUseCase,
    private val bookmarkMovieUseCase: BookmarkMovieUseCase,
    private val deleteBookmarkMovieUseCase: DeleteBookmarkMovieUseCase,
) : BaseViewModel() {
    private val movieId: Int =
        checkNotNull(savedStateHandle.toRoute<AppRoute.MovieDetail>().movieId)

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieDetail()
        getMovieCredits()
        hasBookmarkMovie()
    }

    fun bookmarkMovie() {
        val movie = _uiState.value.movie ?: return
        launchUseCase(
            bookmarkMovieUseCase,
            BookmarkMovieUseCase.Input(movie),
            showLoading = false,
        ) {
            _uiState.update { it.copy(hasBookmark = true) }
        }
    }

    fun unBookmarkMovie() {
        launchUseCase(
            deleteBookmarkMovieUseCase,
            DeleteBookmarkMovieUseCase.Input(movieId),
            showLoading = false,
        ) {
            _uiState.update { it.copy(hasBookmark = false) }
        }
    }

    private fun hasBookmarkMovie() {
        launchUseCase(
            hasBookmarkMovieUseCase,
            HasBookmarkMovieUseCase.Input(movieId),
        ) { hasBookmark ->
            _uiState.update { it.copy(hasBookmark = hasBookmark) }
        }
    }

    private fun getMovieDetail() {
        launchUseCase(
            getMovieDetailUseCase,
            GetMovieDetailUseCase.Input(movieId = movieId),
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
