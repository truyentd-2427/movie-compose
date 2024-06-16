package com.truyentd.moviecompose.presentation.screens.moviedetail

import androidx.lifecycle.SavedStateHandle
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
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : BaseViewModel() {
    private val movieId: String = checkNotNull(savedStateHandle["movieId"])

    private val _movieDetail = MutableStateFlow<MovieData?>(null)
    val movieDetail = _movieDetail.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val movie = movieRepository.getMovieDetail(movieId.toInt())
                _movieDetail.update { movie }
            } catch (throwable: Throwable) {
                // No-op
            }
        }
    }
}
