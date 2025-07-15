package com.ai.integrator.feature.dialogue.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ai.integrator.feature.dialogue.screen.home.component.modellist.DialogueHomeModelList
import com.ai.integrator.feature.dialogue.screen.home.component.topbar.DialogueHomeTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DialogueHomeScreen(
    onModelItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DialogueHomeViewModel = koinViewModel(),
) {
    // todo opt collectAsState
    val modelList by viewModel.modelList.collectAsState()

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(colorScheme.primaryContainer),
    ) {
        DialogueHomeTopBar()
        DialogueHomeModelList(
            modelList = modelList,
            onModelItemClick = onModelItemClick,
        )
    }
}