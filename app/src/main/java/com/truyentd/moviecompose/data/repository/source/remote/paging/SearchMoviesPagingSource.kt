package com.truyentd.moviecompose.data.repository.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.data.repository.source.remote.api.AuthApi
import com.truyentd.moviecompose.data.repository.source.remote.api.helper.execute
import kotlinx.coroutines.delay

class SearchMoviesPagingSource(
    private val authApi: AuthApi,
    private val keyword: String,
    private val pageSize: Int,
) : PagingSource<Int, MovieData>() {

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        return try {
            val page = params.key ?: 1
            delay(3000)
            val response = authApi.execute { searchMovies(keyword = keyword, page) }
            val nextPage = if (page + 1 <= (response.totalPages ?: 0)) page + 1 else null
            LoadResult.Page(
                data = response.data,
                prevKey = null,
                nextKey = nextPage,
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}