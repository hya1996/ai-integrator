package com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DialogueSessionItemData(
    val sessionTitle: String,
    val lastActiveTs: Long,
)

@Composable
fun DialogueSessionItem(
    itemData: DialogueSessionItemData,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = itemData.sessionTitle,
            color = colorScheme.primary,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 12.dp)
        )
    }
}