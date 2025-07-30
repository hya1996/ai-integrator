package com.ai.integrator.feature.dialogue.screen.detail.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.integrator.core.ui.theme.AITheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogueDetailTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String = "",
    onBackClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(
                color = colorScheme.primaryContainer
            ),
    ) {
        Image(
            imageVector = Icons.Rounded.ArrowBackIosNew,
            contentDescription = null,
            modifier = Modifier
                .width(36.dp)
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .clickable(
                    onClick = onBackClick,
                    indication = null,
                    interactionSource = null,
                )
                .padding(start = 12.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.align(Alignment.Center),
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorScheme.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = colorScheme.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        HorizontalDivider(
            thickness = 0.5.dp,
            color = colorScheme.outline,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun DialogueDetailTopBarPreview() {
    AITheme {
        DialogueDetailTopBar(
            title = "DeepSeek",
            subtitle = "DeepSeek-R1-Distill-Llama-8B",
        )
    }
}