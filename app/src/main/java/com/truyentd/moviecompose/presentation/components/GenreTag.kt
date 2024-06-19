package com.truyentd.moviecompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun GenreTagPreview() {
    GenreTag("Action")
}

@Composable
fun GenreTag(genre: String) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(color = AppColors.Fog, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 4.dp, horizontal = 12.dp)
    ) {
        Text(
            text = genre,
            fontSize = 10.sp,
            color = AppColors.Portage
        )
    }
}
