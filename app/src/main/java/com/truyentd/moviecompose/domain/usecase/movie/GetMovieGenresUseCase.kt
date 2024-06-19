package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.GenreData
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import javax.inject.Inject

class GetMovieGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : AsyncNoInputUseCase<List<GenreData>>() {

    override suspend fun buildUseCase(): List<GenreData> {
        return movieRepository.getMovieGenre()
    }
}
