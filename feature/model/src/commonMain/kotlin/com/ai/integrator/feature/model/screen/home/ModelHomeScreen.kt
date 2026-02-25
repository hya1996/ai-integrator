package com.ai.integrator.feature.model.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.ai.integrator.feature.model.dialog.addmodel.AddModelDialog
import com.ai.integrator.feature.model.screen.home.component.modellist.ModelHomeModelList
import com.ai.integrator.feature.model.screen.home.component.topbar.ModelHomeTopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ModelHomeScreen(
    onModelItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ModelHomeViewModel = koinViewModel(),
) {
    var showAddModelDialog by remember { mutableStateOf(false) }
    val modelPagingItems = viewModel.modelList.collectAsLazyPagingItems()

    if (showAddModelDialog) {
        AddModelDialog(
            onDismiss = {
                showAddModelDialog = false
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(colorScheme.primaryContainer),
    ) {
        ModelHomeTopBar(
            onAddModelClick = { showAddModelDialog = true }
        )
        ModelHomeModelList(
            modelPagingItems = modelPagingItems,
            onModelItemClick = onModelItemClick,
        )
    }
}