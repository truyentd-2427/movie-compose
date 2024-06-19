package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncNoInputUseCase<List<MovieData>>() {

    override suspend fun buildUseCase(): List<MovieData> {
        return movieRepository.getNowPlayingMovies()
    }
}
