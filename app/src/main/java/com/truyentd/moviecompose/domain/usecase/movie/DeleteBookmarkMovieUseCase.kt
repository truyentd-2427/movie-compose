package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import javax.inject.Inject

class DeleteBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncUseCase<DeleteBookmarkMovieUseCase.Input, Unit>() {

    data class Input(val movieId: Int) : BaseInput()

    override suspend fun buildUseCase(input: Input) {
        movieRepository.deleteBookmarkMovie(input.movieId)
    }
}
