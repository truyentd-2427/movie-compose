package com.truyentd.moviecompose.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.navigation.BaseDestination
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.dialog.AppErrorDialog
import com.truyentd.moviecompose.presentation.screens.search.components.SearchMovieItem
import com.truyentd.moviecompose.presentation.theme.AppColors
import com.truyentd.moviecompose.shared.extension.collectAsEffect

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen {}
}

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigator: (BaseDestination) -> Unit,
) {
    viewModel.navigator.collectAsEffect { destination -> navigator(destination) }

    val queryText by viewModel.queryText.collectAsStateWithLifecycle()
    val moviePagingItems = viewModel.searchUiState.collectAsLazyPagingItems()
    val errorState by viewModel.errorState.collectAsStateWithLifecycle()

    if (errorState.shouldShowDialog) {
        AppErrorDialog(
            throwable = errorState.throwable,
            onDismissRequest = viewModel::dismissErrorDialog,
        )
    }

    SearchScreenContent(
        queryText = queryText,
        moviePagingItems = moviePagingItems,
        onQueryTextChanged = viewModel::onQueryTextChanged,
        onMovieClick = viewModel::goToMovieDetail
    )
}

@Composable
private fun SearchScreenContent(
    queryText: String = "",
    moviePagingItems: LazyPagingItems<MovieData>,
    onQueryTextChanged: ((String) -> Unit)? = null,
    onMovieClick: ((MovieData) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
    ) {
        SearchHeader(queryText = queryText, onQueryTextChanged = onQueryTextChanged)
        LoadingBox(isLoading = moviePagingItems.loadState.refresh is LoadState.Loading) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.search_result),
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
                    items(moviePagingItems.itemCount) { index ->
                        SearchMovieItem(
                            movie = moviePagingItems[index],
                            onMovieClick = onMovieClick,
                        )
                    }
                    if (moviePagingItems.loadState.append is LoadState.Loading) {
                        item {
                            BottomLoading()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchHeader(
    queryText: String,
    onQueryTextChanged: ((String) -> Unit)? = null,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.search),
            textAlign = TextAlign.Start,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = AppColors.Violet,
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            value = queryText,
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = AppColors.WhiteLilac,
                cursorColor = AppColors.Black,
                disabledLabelColor = AppColors.WhiteLilac,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(24.dp),
            leadingIcon = {
                Icon(Icons.Filled.Search, "", tint = AppColors.Violet)
            },
            onValueChange = { value ->
                onQueryTextChanged?.invoke(value)
            },
            placeholder = { Text(stringResource(id = R.string.enter_keyword)) },
        )
    }
}

@Composable
private fun BottomLoading() {
    return Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp)
    ) {
        CircularProgressIndicator()
    }
}
