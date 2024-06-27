package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import javax.inject.Inject

class BookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncUseCase<BookmarkMovieUseCase.Input, Unit>() {

    data class Input(val movie: MovieData) : BaseInput()

    override suspend fun buildUseCase(input: Input) {
        movieRepository.saveBookmarkMovie(input.movie)
    }
}
