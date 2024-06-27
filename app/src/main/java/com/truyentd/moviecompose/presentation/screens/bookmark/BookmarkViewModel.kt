package com.truyentd.moviecompose.presentation.screens.bookmark

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.usecase.movie.GetBookmarkMoviesUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
}
