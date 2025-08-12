@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class, ExperimentalCoroutinesApi::class)

package com.ai.integrator.data.dialogue.session

import com.ai.integrator.core.framework.flow.asState
import com.ai.integrator.core.framework.log.Log
import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueSession
import com.ai.integrator.data.dialogue.repository.DialogueMessageRepository
import com.ai.integrator.data.dialogue.repository.DialogueSessionRepository
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.message.isSentByMe
import com.ai.integrator.im.session.IMSessionController
import com.ai.integrator.user.uid.myUid
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class DialogueSessionController(
    private val modelId: Long,
    private val dialogueSessionRepo: DialogueSessionRepository,
    private val dialogueMessageRepo: DialogueMessageRepository
) : IMSessionController<DialogueSession>(TAG) {
    override val sessions: StateFlow<Map<String, DialogueSession>> = dialogueSessionRepo
        .getDialogueSessionsByModelId(modelId)
        .map {
            if (it.isEmpty()) {
                createSession()
                emptyMap()
            } else {
                it.associateBy { session -> session.sessionId }
            }
        }
        .asState(sessionScope, emptyMap())

    private val selectedSessionId = MutableStateFlow("")
    override val curSession: StateFlow<DialogueSession?> = combine(
        sessions,
        selectedSessionId
    ) { sessions, sessionId ->
        if (sessionId.isNotEmpty()) {
            sessions[sessionId]
        } else sessions.values.firstOrNull()
    }.asState(sessionScope, null)

    override val curMessages: StateFlow<List<IMMessage<*>>> = curSession
        .filterNotNull()
        .distinctUntilChangedBy { it.sessionId }
        .flatMapLatest { dialogueMessageRepo.getDialogueMessagesBySessionId(it.sessionId) }
        .asState(sessionScope, emptyList())

    val curSessionId: String
        get() = curSession.value?.sessionId ?: ""

    override val supportMessageHandlers: List<HandlerKey<*>> = listOf(
        DialogueMessageHandler.Key
    )

    override fun onReceiveMessage(message: IMMessage<*>) {
        if (message !is DialogueMessage) {
            Log.e(TAG, "receive message type not DialogueMessage, return")
            return
        }

        if (!message.isSentByMe) {
            return
        }

        sessionScope.launch {
            sessions.value[message.sessionId]?.let {
                val updatedSession = it.copy(lastActiveTs = Clock.System.now().toEpochMilliseconds())
                dialogueSessionRepo.upsertDialogueSession(updatedSession)
            }
        }
    }

    fun createSession() {
        val session =  DialogueSession(
            sessionId = Uuid.random().toString(),
            participants = listOf(
                IMIdentity(id = myUid.uid, type = IdentityType.USER),
                IMIdentity(id = modelId, type = IdentityType.AI)
            )
        )
        sessionScope.launch {
            dialogueSessionRepo.upsertDialogueSession(session)
            selectedSessionId.value = session.sessionId
        }
    }

    fun switchSession(sessionId: String) {
        if (selectedSessionId.value == sessionId) {
            return
        }

        selectedSessionId.value = sessionId
    }

    companion object {
        private const val TAG = "DialogueSessionController"
    }
}