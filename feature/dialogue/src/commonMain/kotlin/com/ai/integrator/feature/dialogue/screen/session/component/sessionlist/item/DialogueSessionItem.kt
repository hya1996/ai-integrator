@file:OptIn(ExperimentalTime::class)

package com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item

import ai_integrator.feature.dialogue.generated.resources.Res
import ai_integrator.feature.dialogue.generated.resources.feature_dialogue_session_record_item_default_title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.ai.integrator.core.framework.time.toDateTimeFormat
import com.ai.integrator.core.ui.theme.AITheme
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueSession
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

data class DialogueSessionItemData(
    val sessionId: String,
    val sessionTitle: String,
    val lastActiveTs: Long,
    val isSelected: Boolean
)

suspend fun DialogueSession.toDialogueSessionItemData(
    lastMessage: DialogueMessage?,
    selectedSessionId: String,
): DialogueSessionItemData {
    return DialogueSessionItemData(
        sessionId = sessionId,
        sessionTitle = lastMessage?.content?.text
            ?: getString(Res.string.feature_dialogue_session_record_item_default_title),
        lastActiveTs = lastActiveTs,
        isSelected = sessionId == selectedSessionId
    )
}

@Composable
fun DialogueSessionItem(
    itemData: DialogueSessionItemData,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(
                color = if (itemData.isSelected)
                    colorScheme.tertiaryContainer else colorScheme.primaryContainer
            )
            .padding(horizontal = 16.dp)
            .clickable(
                onClick = { onItemClick(itemData.sessionId) },
                indication = null,
                interactionSource = null,
            )
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
        Text(
            text = itemData.lastActiveTs.toDateTimeFormat(),
            color = colorScheme.secondary,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 3.dp)
        )
    }
}

@Preview
@Composable
fun DialogueSessionItemPreview() {
    AITheme {
        DialogueSessionItem(
            itemData = DialogueSessionItemData(
                sessionId = "",
                sessionTitle = "测试标题",
                lastActiveTs = 100000L,
                isSelected = false
            ),
            onItemClick = {}
        )
    }
}