package com.ai.integrator.feature.dialogue.screen.detail

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.dialogue.model.DIALOGUE_ROLE_USER
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.model.DialogueModelInfo
import com.ai.integrator.data.dialogue.repository.DialogueDetailRepository
import com.ai.integrator.data.dialogue.repository.DialogueModelRepository
import com.ai.integrator.data.dialogue.session.DialogueMessageHandler
import com.ai.integrator.data.dialogue.session.DialogueSessionController
import com.ai.integrator.im.IMCenter
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.MessageStatus
import com.ai.integrator.user.uid.myUid
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.UUID

@HiltViewModel(assistedFactory = DialogueDetailViewModel.Factory::class)
class DialogueDetailViewModel @AssistedInject constructor(
    @Assisted private val modelId: Long,
    private val dialogueModelRepo: DialogueModelRepository,
    private val dialogueDetailRepo: DialogueDetailRepository
) : BaseViewModel() {
    private val _inputContent = MutableStateFlow("")
    val inputContent = _inputContent.asStateFlow()

    val enableSend: StateFlow<Boolean> = inputContent
        .map { it.isNotEmpty() }
        .asState(viewModelScope, false)

    private val _modelInfo = MutableStateFlow<DialogueModelInfo?>(null)
    val modelInfo = _modelInfo.asStateFlow()

    private val sessionController = DialogueSessionController(modelId)
    val messages: StateFlow<List<DialogueMessage>> = sessionController.curSession
        .map { it?.messages?.map { msg -> msg as DialogueMessage }?.reversed() ?: emptyList() }
        .asState(viewModelScope, emptyList())

    init {
        initModelInfo()
        sessionController.init()
        IMCenter.registerMessageHandler(DialogueMessageHandler.Key,
            DialogueMessageHandler(viewModelScope, dialogueDetailRepo))
    }

    private fun initModelInfo() = viewModelScope.launch {
        _modelInfo.value = dialogueModelRepo.getModelById(modelId)
    }

    fun updateInputContent(content: String) {
        _inputContent.value = content
    }

    fun clearInputContent() {
        _inputContent.value = ""
    }

    fun sendDialogueMessage() = viewModelScope.launch {
        val sendMsg = DialogueMessage(
            messageId = UUID.randomUUID().toString(),
            sessionId = sessionController.curSessionId,
            sender = IMIdentity(myUid.uid, IdentityType.USER),
            receiver = IMIdentity(modelId, IdentityType.AI),
            content = DialogueMessageContent(DIALOGUE_ROLE_USER, _inputContent.value),
            timestamp = System.currentTimeMillis(),
            status = MessageStatus.SENDING
        )
        clearInputContent()
        IMCenter.dispatchMessages(DialogueMessageHandler.Key, sessionController.curMessages + sendMsg)
    }

    override fun onCleared() {
        super.onCleared()
        IMCenter.unregisterMessageHandler(DialogueMessageHandler.Key)
    }

    @AssistedFactory
    interface Factory {
        fun create(modelId: Long): DialogueDetailViewModel
    }

    companion object {
        private const val TAG = "DialogueDetailViewModel"
    }
}