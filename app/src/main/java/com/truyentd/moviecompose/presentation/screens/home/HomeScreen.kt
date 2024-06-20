package com.truyentd.moviecompose.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.navigation.movie.MovieDestination
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.components.SectionTitle
import com.truyentd.moviecompose.presentation.screens.home.components.NowShowingMovieItem
import com.truyentd.moviecompose.presentation.screens.home.components.PopularMovieItem
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigator: ((BaseDestination) -> Unit)? = null
) {
    val uiState: HomeUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isLoading: Boolean by viewModel.isLoading.collectAsStateWithLifecycle()

    HomeScreenContent(uiState = uiState, isLoading = isLoading, navigator = navigator)
}

@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    isLoading: Boolean,
    navigator: ((BaseDestination) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
            .background(AppColors.White)
    ) {
        TopHeader()
        LoadingBox(
            isLoading = isLoading,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                SectionTitle(
                    title = stringResource(id = R.string.now_showing),
                    onSeeMoreClick = {
                        // TODO Go to see more screen
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                ListNowShowingMovies(
                    movies = uiState.nowPlayingMovies,
                    onMovieClick = {
                        navigator?.invoke(MovieDestination.MovieDetail.createRoute(it.id.toString()))
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(
                    title = stringResource(id = R.string.popular),
                    onSeeMoreClick = {
                        // TODO Go to see more screen
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                LisPopularMovies(
                    movies = uiState.popularMovies,
                    onMovieClick = {
                        navigator?.invoke(MovieDestination.MovieDetail.createRoute(it.id.toString()))
                    },
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun TopHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(60.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            modifier = Modifier.size(24.dp),
            tint = AppColors.Violet,
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = AppColors.Violet,
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun ListNowShowingMovies(
    movies: List<MovieData>,
    onMovieClick: ((MovieData) -> Unit)? = null
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) {
        items(movies.size) { index ->
            NowShowingMovieItem(movie = movies[index], onMovieClick = onMovieClick)
        }
    }
}

@Composable
private fun LisPopularMovies(movies: List<MovieData>, onMovieClick: ((MovieData) -> Unit)? = null) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        repeat(movies.size) { index ->
            PopularMovieItem(movies[index], onMovieClick = onMovieClick)
        }
    }
}
