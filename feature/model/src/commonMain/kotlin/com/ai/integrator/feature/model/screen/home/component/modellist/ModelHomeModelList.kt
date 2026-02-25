package com.ai.integrator.feature.model.screen.home.component.modellist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.ai.integrator.feature.model.screen.home.component.modellist.item.ModeInfoItem
import com.ai.integrator.feature.model.screen.home.component.modellist.item.ModelInfoItemData

@Composable
fun ModelHomeModelList(
    modelPagingItems: LazyPagingItems<ModelInfoItemData>,
    onModelItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(count = modelPagingItems.itemCount) { index ->
            val item = modelPagingItems[index]
            if (item != null) {
                ModeInfoItem(
                    itemData = item,
                    onClick = onModelItemClick,
                )
            }
        }
    }
}