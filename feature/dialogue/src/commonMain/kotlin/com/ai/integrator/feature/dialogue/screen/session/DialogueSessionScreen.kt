package com.ai.integrator.feature.dialogue.screen.session

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.DialogueSessionList
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItemData
import com.ai.integrator.feature.dialogue.screen.session.component.topbar.DialogueSessionTopBar

@Composable
fun DialogueSessionScreen(
    modelId: Long,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth(0.75f)
            .fillMaxHeight()
    ) {
        DialogueSessionTopBar()
        DialogueSessionList(listOf( // todo delete
            DialogueSessionItemData("test1", 1000L),
            DialogueSessionItemData("非常长非常长非常长非常长非常长非常长非常长的标题", 1000L)
        ))
    }
}