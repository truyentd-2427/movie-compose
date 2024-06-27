package com.truyentd.moviecompose.presentation.screens.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.presentation.components.LoadingBox
import com.truyentd.moviecompose.presentation.components.SectionTitle
import com.truyentd.moviecompose.presentation.screens.moviedetail.components.CastItem
import com.truyentd.moviecompose.presentation.screens.search.components.CategoryTag
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen()
}

@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    MovieDetailContent(
        uiState = uiState,
        isLoading = isLoading,
        onBookmarkClick = { isBookmark ->
            if (isBookmark) viewModel.bookmarkMovie() else viewModel.unBookmarkMovie()
        },
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun MovieDetailContent(
    uiState: MovieDetailUiState,
    isLoading: Boolean,
    onBookmarkClick: ((Boolean) -> Unit)? = null,
) {
    LoadingBox(isLoading = isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.White)
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = uiState.movie?.backdropUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-48).dp)
                    .padding(vertical = 24.dp)
                    .background(
                        color = AppColors.White,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = uiState.movie?.title.orEmpty(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onBookmarkClick?.invoke(!uiState.hasBookmark) },
                        painter = painterResource(id = if (uiState.hasBookmark) R.drawable.ic_bookmarked else R.drawable.ic_bookmark_unselected),
                        tint = Color.Unspecified,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        modifier = Modifier.size(12.dp),
                        contentDescription = null,
                        tint = AppColors.LightningYellow
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = uiState.movie?.ratingFormatted.orEmpty(),
                        fontSize = 12.sp,
                        color = AppColors.DustyGray,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                FlowRow(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(uiState.movie?.genres.orEmpty().size) { index ->
                        CategoryTag(
                            category = uiState.movie?.genres?.getOrNull(index)?.name.orEmpty(),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                MovieBasicInfo(
                    length = uiState.movie?.lengthFormatted.orEmpty(),
                    language = uiState.movie?.spokenLanguages?.firstOrNull()?.englishName.orEmpty(),
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .wrapContentSize(),
                    text = stringResource(id = R.string.description),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = uiState.movie?.overview.orEmpty(),
                    fontSize = 14.sp,
                    color = AppColors.DustyGray,
                )
                Spacer(modifier = Modifier.height(24.dp))
                SectionTitle(title = stringResource(id = R.string.cast))
                Spacer(modifier = Modifier.height(12.dp))
                ListCasts(uiState.casts)
            }
        }
    }
}

@Composable
private fun MovieBasicInfo(length: String, language: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(id = R.string.length),
                fontSize = 12.sp,
                color = AppColors.DustyGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = length,
                fontSize = 12.sp,
                color = AppColors.Black
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(id = R.string.language),
                fontSize = 12.sp,
                color = AppColors.DustyGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = language,
                fontSize = 12.sp,
                color = AppColors.Black
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
    }
}

@Composable
private fun ListCasts(casts: List<CastData>, onCastClick: (() -> Unit)? = null) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) {
        items(casts.size) { index ->
            CastItem(cast = casts[index], onCastClick = onCastClick)
        }
    }
}
