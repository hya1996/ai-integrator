package com.ai.integrator.feature.dialogue.screen.detail.component.messagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.im.session.IMSession

@Composable
fun DialogueDetailMessageList(
    session: IMSession?,
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
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(session?.messages ?: emptyList()) { message ->
                when (message) {
                    is DialogueMessage -> {
                        Text(
                            text = message.content.content,
                            color = colorScheme.primary,
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    else -> {
                        //todo
                    }
                }
            }
        }
    }
}