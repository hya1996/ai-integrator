package com.ai.integrator.feature.dialogue.screen.home

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.dialogue.repository.DialogueModelRepository
import com.ai.integrator.feature.dialogue.screen.home.component.modellist.DialogueHomeModelItemData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DialogueHomeViewModel @Inject constructor(
    private val dialogueModelRepo: DialogueModelRepository
) : BaseViewModel() {
    val modelList: StateFlow<List<DialogueHomeModelItemData>> = dialogueModelRepo.getModelList()
        .map { it.map { modelInfo -> DialogueHomeModelItemData(modelInfo) } }
        .asState(viewModelScope, emptyList())
}