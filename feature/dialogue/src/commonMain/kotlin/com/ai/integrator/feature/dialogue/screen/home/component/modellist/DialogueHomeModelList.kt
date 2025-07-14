package com.ai.integrator.feature.dialogue.screen.home.component.modellist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ai.integrator.core.ui.theme.AITheme
import com.ai.integrator.data.dialogue.model.DialogueModelInfo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DialogueHomeModelList(
    modelList: List<DialogueHomeModelItemData>,
    onModelItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(modelList) {
            DialogueHomeModeItem(
                itemData = it,
                onClick = onModelItemClick,
            )
        }
    }
}

@Preview
@Composable
fun DialogueHomeModelListPreview() {
    val listData = listOf(
        DialogueHomeModelItemData(
            modelInfo = DialogueModelInfo(
                modelId = 1000L,
                simpleName = "DeepSeek",
                modelName = "deepseek-ai/DeepSeek-R1-Distill-Qwen-7B",
                iconUrl = "https://play-lh.googleusercontent.com/d2zqBFBEymSZKaVg_dRo1gh3hBFn7_Kl9rO74xkDmnJeLgDW0MoJD3cUx0QzZN6jdsg=w480-h960-rw",
                intro = "DeepSeek-R1-Distill-Qwen-7B",
            ),
        )
    )

    AITheme {
        DialogueHomeModelList(
            modelList = listData,
            onModelItemClick = {},
        )
    }
}