package com.truyentd.moviecompose.presentation.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun SearchMovieItemPreview() {
    SearchMovieItem(MovieData())
}

@Composable
fun SearchMovieItem(
    movie: MovieData?,
    onMovieClick: ((MovieData) -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                movie?.let { onMovieClick?.invoke(it) }
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(85.dp)
                .height(120.dp)
                .clip(
                    shape = RoundedCornerShape(8.dp)
                ),
            model = movie?.posterUrl.orEmpty(),
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
                text = movie?.title.orEmpty(),
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
                    text = movie?.ratingFormatted.orEmpty(),
                    fontSize = 12.sp,
                    color = AppColors.DustyGray,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CategoryTag("Action")
                Spacer(modifier = Modifier.width(4.dp))
                CategoryTag("Fiction")
                Spacer(modifier = Modifier.width(4.dp))
                CategoryTag("Fantasy")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    modifier = Modifier.size(12.dp),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = movie?.lengthFormatted.orEmpty(),
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
fun CategoryTag(category: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = AppColors.Fog, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 4.dp, horizontal = 12.dp)
    ) {
        Text(
            text = category,
            fontSize = 10.sp,
            color = AppColors.Portage
        )
    }
}