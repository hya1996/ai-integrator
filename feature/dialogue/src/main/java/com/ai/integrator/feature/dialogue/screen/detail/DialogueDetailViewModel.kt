package com.ai.integrator.feature.dialogue.screen.detail

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.common.onFailure
import com.ai.integrator.core.framework.common.onSuccess
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.dialogue.model.DIALOGUE_ROLE_USER
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueModelInfo
import com.ai.integrator.data.dialogue.repository.DialogueDetailRepository
import com.ai.integrator.data.dialogue.repository.DialogueModelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DialogueDetailViewModel : BaseViewModel() {
    private val _inputContent = MutableStateFlow("")
    val inputContent = _inputContent.asStateFlow()

    val enableSend: StateFlow<Boolean> = inputContent
        .map { it.isNotEmpty() }
        .asState(viewModelScope, false)

    private val _reply = MutableStateFlow("")
    val reply = _reply.asStateFlow()

    private val _modelInfo = MutableStateFlow<DialogueModelInfo?>(null)
    val modelInfo = _modelInfo.asStateFlow()

    private val dialogueModelRepo  = DialogueModelRepository()
    private val dialogueDetailRepo = DialogueDetailRepository()

    fun init(modelId: Long) {
        initModelInfo(modelId)
    }

    private fun initModelInfo(modelId: Long) = viewModelScope.launch {
        _modelInfo.value = dialogueModelRepo.getModelById(modelId)
    }

    fun updateInputContent(content: String) {
        _inputContent.value = content
    }

    fun sendDialogueMessage() = viewModelScope.launch {
        val curModelInfo = modelInfo.value ?: return@launch
        val message = DialogueMessage(
            role = DIALOGUE_ROLE_USER,
            content = _inputContent.value
        )
        dialogueDetailRepo.reqDialogueReply(curModelInfo.modelName, listOf(message)).collect {
            it.onSuccess { content ->
                _reply.value += content
            }.onFailure { code, message ->
                Log.e(TAG, "reqDialogueReply fail code: $code, message: $message")
            }
        }
    }

    companion object {
        private const val TAG = "DialogueDetailViewModel"
    }
}