package com.ai.integrator.feature.dialogue.screen.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ai.integrator.feature.dialogue.dialog.apikey.PlatformApiKeyDialog
import com.ai.integrator.feature.dialogue.screen.model.component.modellist.PlatformModelList
import com.ai.integrator.feature.dialogue.screen.model.component.topbar.PlatformModelTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PlatformModelScreen(
    onModelItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlatformModelViewModel = koinViewModel(),
) {
    val needInputApiKey by viewModel.needInputApiKey.collectAsStateWithLifecycle()
    val modelList by viewModel.modelList.collectAsStateWithLifecycle()

    if (needInputApiKey) {
        PlatformApiKeyDialog()
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(colorScheme.primaryContainer),
    ) {
        PlatformModelTopBar()
        PlatformModelList(
            modelList = modelList,
            onModelItemClick = onModelItemClick,
        )
    }
}