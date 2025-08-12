package com.ai.integrator.feature.dialogue.screen.session.component.sessionlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItem
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItemData

@Composable
fun DialogueSessionList(
    dataList: List<DialogueSessionItemData>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dataList) { itemData ->
            DialogueSessionItem(
                itemData = itemData,
                onItemClick = onItemClick
            )
        }
    }
}