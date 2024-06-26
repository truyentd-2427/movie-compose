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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.screens.search.components.SearchMovieItem
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    SearchScreen()
}

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val moviePagingItems = viewModel.searchUiState.collectAsLazyPagingItems()

    SearchScreenContent(
        moviePagingItems = moviePagingItems,
        onQueryTextChanged = {
            viewModel.onQueryTextChanged(it)
        },
    )
}

@Composable
private fun SearchScreenContent(
    moviePagingItems: LazyPagingItems<MovieData>,
    onQueryTextChanged: ((String) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.White)
    ) {
        SearchHeader(onQueryTextChanged = onQueryTextChanged)
        LoadingBox(isLoading = moviePagingItems.loadState.refresh is LoadState.Loading) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Search Result:",
                    modifier = Modifier.wrapContentSize().padding(horizontal = 24.dp),
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
                        SearchMovieItem(movie = moviePagingItems[index])
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
private fun SearchHeader(onQueryTextChanged: ((String) -> Unit)? = null) {
    var queryText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "Search",
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
                queryText = value
                onQueryTextChanged?.invoke(value.text)
            },
            placeholder = { Text("Enter keyword") },
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
