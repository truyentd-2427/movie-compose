package com.truyentd.moviecompose.presentation.screens.bookmark

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.usecase.movie.GetBookmarkMoviesUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    getBookmarkMoviesUseCase: GetBookmarkMoviesUseCase,
) : BaseViewModel() {

    val bookmarkMovies = getBookmarkMoviesUseCase().stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun goToMovieDetail(movie: MovieData) {
        launch { _navigator.emit(AppRoute.MovieDetail(movie.id ?: -1)) }
    }
}
