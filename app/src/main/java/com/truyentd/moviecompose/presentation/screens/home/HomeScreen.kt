package com.truyentd.moviecompose.presentation.screens.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.components.SectionTitle
import com.truyentd.moviecompose.presentation.dialog.AppErrorDialog
import com.truyentd.moviecompose.presentation.screens.AppViewModel
import com.truyentd.moviecompose.presentation.screens.LocalAppViewModel
import com.truyentd.moviecompose.presentation.screens.home.components.NowShowingMovieItem
import com.truyentd.moviecompose.presentation.screens.home.components.PopularMovieItem
import com.truyentd.moviecompose.presentation.theme.AppTheme
import com.truyentd.moviecompose.shared.extension.collectAsEffect

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        uiState = HomeUiState(),
        darkTheme = false,
        isLoading = false,
        isRefreshing = false,
    )
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    appViewModel: AppViewModel = LocalAppViewModel.current,
    navigator: (BaseDestination) -> Unit,
) {
    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val errorState by viewModel.errorState.collectAsStateWithLifecycle()
    val darkTheme by appViewModel.isDarkTheme.collectAsStateWithLifecycle()

    if (errorState.shouldShowDialog) {
        AppErrorDialog(
            throwable = errorState.throwable,
            onDismissRequest = viewModel::dismissErrorDialog,
        )
    }

    HomeScreenContent(
        uiState = uiState,
        isLoading = isLoading,
        isRefreshing = isRefreshing,
        darkTheme = darkTheme,
        onMovieClick = viewModel::goToMovieDetail,
        onRefresh = viewModel::onRefresh,
        logout = viewModel::logout,
        switchTheme = appViewModel::switchTheme,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenContent(
    uiState: HomeUiState,
    isLoading: Boolean,
    isRefreshing: Boolean,
    darkTheme: Boolean,
    onMovieClick: ((MovieData) -> Unit)? = null,
    switchTheme: (() -> Unit)? = null,
    onRefresh: (() -> Unit)? = null,
    logout: (() -> Unit)? = null,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { onRefresh?.invoke() },
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        TopHeader(
            darkTheme = darkTheme,
            switchTheme = switchTheme,
            logout = logout,
        )
        LoadingBox(
            isLoading = isLoading,
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                if (uiState.nowPlayingMovies.isNotEmpty()) {
                    SectionTitle(
                        title = stringResource(id = R.string.now_showing),
                        onSeeMoreClick = {
                            // TODO Go to see more screen
                        },
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ListNowShowingMovies(
                    movies = uiState.nowPlayingMovies,
                    onMovieClick = onMovieClick,
                )
                Spacer(modifier = Modifier.height(24.dp))
                if (uiState.popularMovies.isNotEmpty()) {
                    SectionTitle(
                        title = stringResource(id = R.string.popular),
                        onSeeMoreClick = {
                            // TODO Go to see more screen
                        },
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                ListPopularMovies(
                    movies = uiState.popularMovies,
                    onMovieClick = onMovieClick,
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = isRefreshing,
                state = pullRefreshState,
            )
        }
    }
}

@Composable
private fun TopHeader(
    darkTheme: Boolean,
    switchTheme: (() -> Unit)? = null,
    logout: (() -> Unit)? = null,
) {
    val themeIconId = if (darkTheme) R.drawable.ic_sun else R.drawable.ic_moon
    val rotationAngle by animateFloatAsState(targetValue = if (darkTheme) 180f else 0f, label = "")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .height(60.dp)
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .graphicsLayer(rotationZ = rotationAngle)
                .clickable { switchTheme?.invoke() },
            painter = painterResource(id = themeIconId),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.heading5,
        )
        Spacer(modifier = Modifier.size(24.dp))
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable { logout?.invoke() },
            painter = painterResource(id = R.drawable.ic_logout),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null,
        )
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
private fun ListPopularMovies(
    movies: List<MovieData>,
    onMovieClick: ((MovieData) -> Unit)? = null
) {
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
