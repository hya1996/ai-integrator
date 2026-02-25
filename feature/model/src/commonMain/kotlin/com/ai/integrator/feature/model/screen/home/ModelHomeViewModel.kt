package com.ai.integrator.feature.model.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.model.repository.ModelRepository
import com.ai.integrator.feature.model.screen.home.component.modellist.item.ModelInfoItemData
import kotlinx.coroutines.flow.map

class ModelHomeViewModel(
    private val modelRepo: ModelRepository
) : BaseViewModel() {
    val modelList = modelRepo.getModelInfoPagingDataFlow()
        .map { pagingData ->
            pagingData.map { ModelInfoItemData(it) }
        }
        .cachedIn(viewModelScope)
}