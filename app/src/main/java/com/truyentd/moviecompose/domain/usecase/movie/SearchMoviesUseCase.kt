package com.truyentd.moviecompose.domain.usecase.movie

import androidx.paging.PagingData
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.domain.interactor.input.BaseInput
import com.truyentd.moviecompose.domain.repository.MovieRepository
import com.truyentd.moviecompose.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) : FlowUseCase<SearchMoviesUseCase.Input, PagingData<MovieData>>() {

    data class Input(val keyword: String) : BaseInput()

    override suspend fun buildUseCase(input: Input): Flow<PagingData<MovieData>> {
        return movieRepository.searchMovies(input.keyword)
    }
}
