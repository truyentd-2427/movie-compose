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
import com.truyentd.moviecompose.data.model.CastData
import com.truyentd.moviecompose.ui.theme.AppColors

@Composable
fun CastItem(cast: CastData, onCastClick: (() -> Unit)? = null) {
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
            model = cast.profileImageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = cast.name.orEmpty(),
            fontSize = 12.sp,
            color = AppColors.Violet,
            lineHeight = 18.sp,
        )
    }
}
