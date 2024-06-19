package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncUseCase<GetMovieDetailUseCase.Input, MovieData?>() {

    data class Input(val movieId: Int) : BaseInput()

    override suspend fun buildUseCase(input: Input): MovieData? {
        return movieRepository.getMovieDetail(input.movieId)
    }
}
