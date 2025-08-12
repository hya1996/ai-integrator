@file:OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)

package com.ai.integrator.feature.dialogue.screen.message

import androidx.lifecycle.viewModelScope
import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.viewmodel.BaseViewModel
import com.ai.integrator.data.dialogue.model.DIALOGUE_ROLE_USER
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import com.ai.integrator.data.dialogue.repository.DialogueMessageRepository
import com.ai.integrator.data.dialogue.session.DialogueMessageHandler
import com.ai.integrator.data.dialogue.session.DialogueSessionController
import com.ai.integrator.data.platform.model.PlatformModelInfo
import com.ai.integrator.data.platform.repository.PlatformModelRepository
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.DialogueSessionItemData
import com.ai.integrator.feature.dialogue.screen.session.component.sessionlist.item.toDialogueSessionItemData
import com.ai.integrator.im.IMCenter
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.MessageStatus
import com.ai.integrator.user.uid.myUid
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class DialogueMessageViewModel(
    private val modelId: Long,
    private val platformModelRepo: PlatformModelRepository,
    private val dialogueMessageRepo: DialogueMessageRepository
) : BaseViewModel() {
    private val _inputContent = MutableStateFlow("")
    val inputContent = _inputContent.asStateFlow()

    val enableSend: StateFlow<Boolean> = inputContent
        .map { it.isNotEmpty() }
        .asState(viewModelScope, false)

    private val _modelInfo = MutableStateFlow<PlatformModelInfo?>(null)
    val modelInfo = _modelInfo.asStateFlow()

    private val sessionController = KoinPlatform.getKoin().get<DialogueSessionController> { parametersOf(modelId) }
    val messages: StateFlow<List<DialogueMessage>> = sessionController.curMessages
        .map { it.map { msg -> msg as DialogueMessage } }
        .asState(viewModelScope, emptyList())

    val sessionDataList: StateFlow<List<DialogueSessionItemData>> = sessionController.sessions
        .map { sessionMap ->
            sessionMap.map { (sessionId, session) ->
                val lastMessage = dialogueMessageRepo.getLastDialogueMessageSentByMe(sessionId)
                session.toDialogueSessionItemData(lastMessage)
            }
        }
        .asState(viewModelScope, emptyList())

    init {
        initModelInfo()
        IMCenter.registerMessageHandler(DialogueMessageHandler.Key,
            KoinPlatform.getKoin().get<DialogueMessageHandler> { parametersOf(viewModelScope) })
    }

    private fun initModelInfo() = viewModelScope.launch {
        _modelInfo.value = platformModelRepo.getModelById(modelId)
    }

    fun updateInputContent(content: String) {
        _inputContent.value = content
    }

    fun clearInputContent() {
        _inputContent.value = ""
    }

    fun sendDialogueMessage() = viewModelScope.launch {
        val sendMsg = DialogueMessage(
            messageId = Uuid.random().toString(),
            sessionId = sessionController.curSessionId,
            sender = IMIdentity(myUid.uid, IdentityType.USER),
            receiver = IMIdentity(modelId, IdentityType.AI),
            content = DialogueMessageContent(DIALOGUE_ROLE_USER, _inputContent.value),
            timestamp = Clock.System.now().toEpochMilliseconds(),
            status = MessageStatus.SENDING
        )
        clearInputContent()
        IMCenter.dispatchMessages(DialogueMessageHandler.Key, sessionController.curMessages.value + sendMsg)
    }

    override fun onCleared() {
        super.onCleared()
        IMCenter.unregisterMessageHandler(DialogueMessageHandler.Key)
    }

    companion object Companion {
        private const val TAG = "DialogueDetailViewModel"
    }
}