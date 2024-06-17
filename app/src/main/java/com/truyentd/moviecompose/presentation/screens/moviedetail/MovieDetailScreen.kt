package com.truyentd.moviecompose.presentation.screens.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.presentation.screens.home.components.CategoryTag
import com.truyentd.moviecompose.presentation.screens.moviedetail.components.CastItem
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen()
}

@Composable
fun MovieDetailScreen(viewModel: MovieDetailViewModel = hiltViewModel()) {
    val movie: MovieData? by viewModel.movieDetail.collectAsStateWithLifecycle()

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
            model = movie?.backdropUrl,
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
                    text = movie?.title.orEmpty(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark_unselected),
                    modifier = Modifier.size(24.dp),
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
                    text = "${movie?.voteAverage ?: 0}/10 IMDB",
                    fontSize = 12.sp,
                    color = AppColors.DustyGray,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CategoryTag("Action")
                Spacer(modifier = Modifier.width(8.dp))
                CategoryTag("Fiction")
                Spacer(modifier = Modifier.width(8.dp))
                CategoryTag("Fantasy")
            }
            Spacer(modifier = Modifier.height(16.dp))
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
                        text = "Length",
                        fontSize = 12.sp,
                        color = AppColors.DustyGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "2h 28min",
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
                        text = "Language",
                        fontSize = 12.sp,
                        color = AppColors.DustyGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "English",
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
                        text = "Rating",
                        fontSize = 12.sp,
                        color = AppColors.DustyGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "PG-13",
                        fontSize = 12.sp,
                        color = AppColors.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .wrapContentSize(),
                text = "Description",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = movie?.overview.orEmpty(),
                fontSize = 14.sp,
                color = AppColors.DustyGray,
            )
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle(title = "Cast")
            Spacer(modifier = Modifier.height(12.dp))
            ListCasts()
        }
    }
}

@Composable
private fun SectionTitle(title: String, onSeeMoreClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = AppColors.Violet
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFE5E4EA)
                )
                .clip(RoundedCornerShape(16.dp))
                .clickable(
                    onClick = {
                        onSeeMoreClick?.invoke()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                text = "See more",
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = Color(0xFFAAA9B1)
            )
        }
    }
}

@Composable
private fun ListCasts(onCastClick: (() -> Unit)? = null) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) {
        items(10) {
            CastItem(onCastClick = onCastClick)
        }
    }
}
