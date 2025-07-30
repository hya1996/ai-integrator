package com.ai.integrator.feature.dialogue.screen.home

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.dialogue.repository.DialogueModelRepository
import com.ai.integrator.feature.dialogue.screen.home.component.modellist.DialogueHomeModelItemData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

class DialogueHomeViewModel(
    private val dialogueModelRepo: DialogueModelRepository
) : BaseViewModel() {
    val needInputApiKey: StateFlow<Boolean> = dialogueModelRepo.apiKeyFlow.map {
        it.isEmpty()
    }.asState(viewModelScope, false)

    val modelList: StateFlow<List<DialogueHomeModelItemData>> = dialogueModelRepo.getModelList()
        .map { it.map { modelInfo -> DialogueHomeModelItemData(modelInfo) } }
        .asState(viewModelScope, emptyList())

    fun updateApiKey(apiKey: String) {
        dialogueModelRepo.updateApiKey(apiKey)
    }
}