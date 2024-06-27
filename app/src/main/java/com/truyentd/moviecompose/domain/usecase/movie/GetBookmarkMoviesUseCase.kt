package com.truyentd.moviecompose.domain.usecase.movie

import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.AsyncNoInputUseCase
import com.truyentd.moviecompose.domain.usecase.base.FlowNoInputUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : FlowNoInputUseCase<List<MovieData>>() {

    override fun buildUseCase(): Flow<List<MovieData>> {
        return movieRepository.getBookmarkMovies()
    }
}
