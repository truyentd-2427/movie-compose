package com.truyentd.moviecompose.presentation.screens.home

import androidx.lifecycle.viewModelScope
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {
    private val _nowShowingMovies = MutableStateFlow<List<MovieData>>(emptyList())
    val nowShowingMovies = _nowShowingMovies.asStateFlow()

    private val _popularMovies = MutableStateFlow<List<MovieData>>(emptyList())
    val popularMovies = _popularMovies.asStateFlow()

    init {
        getNowShowingMovies()
        getPopularMovies()
    }

    private fun getNowShowingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = movieRepository.getNowPlayingMovies().take(10)
                _nowShowingMovies.update { movies }
            } catch (throwable: Throwable) {
                // No-op
            }
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movies = movieRepository.getPopularMovies().take(10)
                _popularMovies.update { movies }
            } catch (throwable: Throwable) {
                // No-op
            }
        }
    }
}
