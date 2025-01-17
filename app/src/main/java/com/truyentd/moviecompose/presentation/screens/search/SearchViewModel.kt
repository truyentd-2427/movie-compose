package com.truyentd.moviecompose.presentation.screens.search

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.usecase.movie.SearchMoviesUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import com.truyentd.moviecompose.shared.constant.KEY_QUERY_TEXT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val searchMoviesUseCase: SearchMoviesUseCase,
) : BaseViewModel() {

    val queryText = savedStateHandle.getStateFlow(key = KEY_QUERY_TEXT, initialValue = "")

    val searchUiState = queryText.debounce(timeoutMillis = 500)
        .distinctUntilChanged()
        .flatMapLatest {
            if (it.isEmpty()) {
                flowOf(PagingData.empty())
            } else {
                searchMoviesUseCase(SearchMoviesUseCase.Input(keyword = it))
            }
        }
        .cachedIn(scope)
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty(),
        )

    fun onQueryTextChanged(keyword: String) {
        savedStateHandle[KEY_QUERY_TEXT] = keyword
    }

    fun goToMovieDetail(movie: MovieData) {
        launch { _navigator.emit(AppRoute.MovieDetail(movie.id ?: -1)) }
    }
}
