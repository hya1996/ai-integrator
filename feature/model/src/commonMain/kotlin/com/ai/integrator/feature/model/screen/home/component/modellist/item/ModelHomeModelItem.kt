package com.ai.integrator.feature.model.screen.home.component.modellist.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.integrator.data.model.model.ModelInfo

data class ModelInfoItemData(
    val modelInfo: ModelInfo,
)

@Composable
fun ModeInfoItem(
    itemData: ModelInfoItemData,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val modelInfo = itemData.modelInfo
    Row(
        modifier = modifier
            .height(76.dp)
            .fillMaxWidth()
            .padding(start = 16.dp)
            .background(colorScheme.primaryContainer)
            .clickable { onClick(modelInfo.modelId) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = Icons.Rounded.SmartToy,
            contentDescription = null,
            modifier = Modifier
                .size(52.dp, 52.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 12.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(75.dp)
            ) {
                Text(
                    text = modelInfo.modelName,
                    color = colorScheme.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 16.dp),
                )
                Text(
                    text = modelInfo.requestUrl,
                    color = colorScheme.secondary,
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 16.dp),
                )
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = colorScheme.outline,
            )
        }
    }
}