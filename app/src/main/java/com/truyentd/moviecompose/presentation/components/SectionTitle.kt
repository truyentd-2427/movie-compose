package com.truyentd.moviecompose.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.truyentd.moviecompose.R
import com.truyentd.moviecompose.ui.theme.AppColors

@Preview(showBackground = true)
@Composable
fun SectionTitlePreview() {
    SectionTitle(stringResource(id = R.string.now_showing))
}

@Composable
fun SectionTitle(title: String, onSeeMoreClick: (() -> Unit)? = null) {
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
                    color = AppColors.Mischka
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
                text = stringResource(id = R.string.see_more),
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = AppColors.Mischka
            )
        }
    }
}
