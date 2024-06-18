package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncUseCase
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncUseCase<GetMovieCreditsUseCase.Input, List<CastData>>() {

    data class Input(val movieId: Int) : BaseInput()

    override suspend fun buildUseCase(input: Input): List<CastData> {
        return movieRepository.getMovieCredits(input.movieId)
    }
}
