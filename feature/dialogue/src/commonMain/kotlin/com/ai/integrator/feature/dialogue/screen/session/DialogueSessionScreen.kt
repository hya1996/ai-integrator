package com.ai.integrator.feature.dialogue.screen.session

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ai.integrator.feature.dialogue.screen.message.DialogueMessageViewModel
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.DialogueSessionList
import com.ai.integrator.feature.dialogue.screen.session.component.topbar.DialogueSessionTopBar
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun DialogueSessionScreen(
    modelId: Long,
    modifier: Modifier = Modifier,
    viewModel: DialogueMessageViewModel // todo temp use opt later
) {
    val sessionDataList by viewModel.sessionDataList.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight()
    ) {
        DialogueSessionTopBar()
        DialogueSessionList(sessionDataList)
    }
}