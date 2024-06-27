package com.truyentd.moviecompose.presentation.screens.search

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.truyentd.moviecompose.domain.usecase.movie.SearchMoviesUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
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

    private val queryText = savedStateHandle.getStateFlow(key = QUERY_TEXT_KEY, initialValue = "")

    val searchUiState = queryText.debounce(timeoutMillis = 500)
        .distinctUntilChanged()
        .flatMapLatest {
            if (it.isEmpty()) {
                flowOf(PagingData.empty())
            } else {
                searchMoviesUseCase(SearchMoviesUseCase.Input(keyword = it))
            }
        }.stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PagingData.empty(),
        ).cachedIn(scope)

    fun onQueryTextChanged(keyword: String) {
        savedStateHandle[QUERY_TEXT_KEY] = keyword
    }

    companion object {
        private const val QUERY_TEXT_KEY = "QUERY_TEXT_KEY"
    }
}
