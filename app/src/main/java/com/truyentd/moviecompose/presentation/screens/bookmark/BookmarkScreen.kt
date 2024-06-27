package com.truyentd.moviecompose.presentation.screens.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.navigation.movie.MovieDestination
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.screens.search.components.SearchMovieItem
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookmarkScreenPreview() {
    BookmarkScreen()
}

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel(),
    navigator: ((BaseDestination) -> Unit)? = null
) {
    val bookmarkMovies by viewModel.bookmarkMovies.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    BookmarkScreenContent(
        bookmarkMovies = bookmarkMovies,
        isLoading = isLoading,
        onMovieClick = { movie ->
            navigator?.invoke(MovieDestination.MovieDetail.createRoute(movie.id.toString()))
        }
    )
}

@Composable
private fun BookmarkScreenContent(
    bookmarkMovies: List<MovieData>,
    isLoading: Boolean,
    onMovieClick: ((MovieData) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = "Bookmark",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = AppColors.Violet,
            )
        }
        LoadingBox(isLoading = isLoading) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bookmark Movies",
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 24.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = AppColors.Violet,
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 24.dp, start = 24.dp, end = 24.dp)
                ) {
                    items(bookmarkMovies.size) { index ->
                        SearchMovieItem(
                            movie = bookmarkMovies[index],
                            onMovieClick = onMovieClick,
                        )
                    }
                }
            }
        }
    }
}
