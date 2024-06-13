package com.truyentd.moviecompose.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.truyentd.moviecompose.data.model.MovieData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    private val _nowShowingMovies = MutableStateFlow<MovieData?>(null)
    val nowShowingMovies = _nowShowingMovies.asStateFlow()
}
