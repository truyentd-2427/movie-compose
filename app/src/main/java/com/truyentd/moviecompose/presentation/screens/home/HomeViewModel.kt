package com.truyentd.moviecompose.presentation.screens.home

import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieGenresUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetNowPlayingMoviesUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetPopularMoviesUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getMovieGenres()
    }

    private fun getMovieGenres() {
        launchUseCase(getMovieGenresUseCase) { genres ->
            getNowShowingMovies(genres)
            getPopularMovies(genres)
        }
    }

    private fun getNowShowingMovies(genres: List<GenreData>) {
        launchUseCase(getNowPlayingMoviesUseCase) { movies ->
            _uiState.update { uiState ->
                val moviesWithGenres = movies.map { movie ->
                    val movieGenres = genres.filter { genre ->
                        movie.genreIds.orEmpty().any { it == genre.id }
                    }
                    movie.copy(genres = movieGenres)
                }
                uiState.copy(nowPlayingMovies = moviesWithGenres)
            }
        }
    }

    private fun getPopularMovies(genres: List<GenreData>) {
        launchUseCase(getPopularMoviesUseCase) { movies ->
            val moviesWithGenres = movies.map { movie ->
                val movieGenres = genres.filter { genre ->
                    movie.genreIds.orEmpty().any { it == genre.id }
                }
                movie.copy(genres = movieGenres)
            }
            _uiState.update { it.copy(popularMovies = moviesWithGenres.take(10)) }
        }
    }
}
