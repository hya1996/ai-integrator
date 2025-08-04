package com.ai.integrator.feature.dialogue.screen.model

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.platform.repository.PlatformModelRepository
import com.ai.integrator.feature.dialogue.screen.model.component.modellist.PlatformModelItemData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class PlatformModelViewModel(
    private val platformModelRepo: PlatformModelRepository
) : BaseViewModel() {
    val needInputApiKey: StateFlow<Boolean> = platformModelRepo.apiKeyFlow.map {
        it.isEmpty()
    }.asState(viewModelScope, false)

    val modelList: StateFlow<List<PlatformModelItemData>> = platformModelRepo.getModelList()
        .map { it.map { modelInfo -> PlatformModelItemData(modelInfo) } }
        .asState(viewModelScope, emptyList())

    fun updateApiKey(apiKey: String) {
        platformModelRepo.updateApiKey(apiKey)
    }
}