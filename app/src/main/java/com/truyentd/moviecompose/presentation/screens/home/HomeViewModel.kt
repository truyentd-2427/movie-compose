package com.truyentd.moviecompose.presentation.screens.home

import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.usecase.movie.GetMovieGenresUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetNowPlayingMoviesUseCase
import com.truyentd.moviecompose.domain.usecase.movie.GetPopularMoviesUseCase
import com.truyentd.moviecompose.domain.usecase.user.LogoutUseCase
import com.truyentd.moviecompose.presentation.base.BaseViewModel
import com.truyentd.moviecompose.presentation.navigation.AppRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieGenresUseCase: GetMovieGenresUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val logoutUseCase: LogoutUseCase,
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
                val fullInfoMovies = movies.map { movie ->
                    val fullInfoGenres = genres.filter { genre ->
                        movie.genreIds.orEmpty().any { it == genre.id }
                    }
                    movie.copy(genres = fullInfoGenres)
                }
                uiState.copy(nowPlayingMovies = fullInfoMovies)
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

    override fun onRefresh() {
        super.onRefresh()
        getMovieGenres()
    }

    fun goToMovieDetail(movie: MovieData?) {
        launch { _navigator.emit(AppRoute.MovieDetail(movie?.id ?: -1)) }
    }

    fun logout() {
        logoutUseCase()
        launch { _navigator.emit(AppRoute.AuthGraph) }
    }
}
