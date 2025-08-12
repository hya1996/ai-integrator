package com.ai.integrator.feature.dialogue.screen.session.component.sessionlist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ai.integrator.core.ui.theme.AITheme
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItem
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItemData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogueSessionList(
    dataList: List<DialogueSessionItemData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(dataList) { itemData ->
            DialogueSessionItem(itemData = itemData)
        }
    }
}

@Preview
@Composable
fun DialogueSessionListPreview() {
    AITheme {
        DialogueSessionList(
            dataList = listOf(DialogueSessionItemData("测试标题", 100000L))
        )
    }
}