package com.ai.integrator.feature.dialogue.screen.detail.component.messagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.feature.dialogue.screen.detail.component.messagelist.item.DialogueMessageReceiveItem
import com.ai.integrator.feature.dialogue.screen.detail.component.messagelist.item.DialogueMessageSendItem
import com.ai.integrator.im.message.isSentByMe

@Composable
fun DialogueDetailMessageList(
    messages: List<DialogueMessage>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = colorScheme.primaryContainer
            ),
    ) {
        LazyColumn(
            state = listState,
            reverseLayout = true,
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(messages) { message ->
                when (message) {
                    is DialogueMessage -> {
                        if (message.isSentByMe) {
                            DialogueMessageSendItem(message)
                        } else {
                            DialogueMessageReceiveItem(message)
                        }
                    }
                    else -> {
                        //todo
                    }
                }
            }
        }
    }
}