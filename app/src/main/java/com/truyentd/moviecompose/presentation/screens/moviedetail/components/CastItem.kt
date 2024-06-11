package com.truyentd.moviecompose.presentation.screens.moviedetail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.truyentd.moviecompose.ui.theme.AppColors

@Composable
fun CastItem(onCastClick: (() -> Unit)? = null) {
    Column(
        modifier = Modifier
            .width(72.dp)
            .wrapContentHeight()
            .clickable {
                onCastClick?.invoke()
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(72.dp)
                .height(72.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            model = "https://vlabinnovation.com/wp-content/uploads/sites/21/ezgif.com-gif-maker-1.png",
            contentDescription = "Translated description of what the image contains",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tom Holland",
            fontSize = 12.sp,
            color = AppColors.Violet
        )
    }
}
