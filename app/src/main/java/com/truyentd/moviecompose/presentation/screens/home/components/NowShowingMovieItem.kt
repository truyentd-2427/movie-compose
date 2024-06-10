package com.truyentd.moviecompose.presentation.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun NowShowingMovieItemPreview() {
    NowShowingMovieItem()
}

@Composable
fun NowShowingMovieItem() {
    Column(
        modifier = Modifier
            .width(143.dp)
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = AppColors.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(143.dp)
                    .height(212.dp),
                model = "https://image.tmdb.org/t/p/original/reEMJA1uzscCbkpeRJeTT2bjqUp.jpg",
                contentDescription = "Translated description of what the image contains"
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Spiderman: No way home",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
