package com.truyentd.moviecompose.presentation.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.data.model.MovieData
import com.truyentd.moviecompose.presentation.screens.search.components.CategoryTag
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun PopularMovieItemPreview() {
    PopularMovieItem(MovieData())
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularMovieItem(movie: MovieData, onMovieClick: ((MovieData) -> Unit)? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onMovieClick?.invoke(movie) }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(85.dp)
                .height(120.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            model = movie.posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = movie.title.orEmpty(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
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
                    text = movie.ratingFormatted,
                    fontSize = 12.sp,
                    color = AppColors.DustyGray,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(movie.genres.orEmpty().size) { index ->
                    CategoryTag(category = movie.genres?.getOrNull(index)?.name.orEmpty())
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
