package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import javax.inject.Inject

class HasBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncUseCase<HasBookmarkMovieUseCase.Input, Boolean>() {

    data class Input(val movieId: Int) : BaseInput()

    override suspend fun buildUseCase(input: Input): Boolean {
        return movieRepository.hasBookmarkMovies(input.movieId)
    }
}
