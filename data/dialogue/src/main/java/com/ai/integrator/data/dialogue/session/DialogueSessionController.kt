package com.ai.integrator.data.dialogue.session

import com.ai.integrator.data.dialogue.model.DialogueMessage
import com.ai.integrator.data.dialogue.model.DialogueSession
import com.ai.integrator.data.dialogue.repository.DialogueDetailRepository
import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.session.IMSessionController
import com.ai.integrator.user.uid.myUid
import kotlinx.coroutines.launch
import java.util.UUID

class DialogueSessionController(
    private val modelId: Long,
    private val dialogueDetailRepo: DialogueDetailRepository
) : IMSessionController<DialogueSession>(modelId, TAG) {
    override val supportMessageHandlers: List<HandlerKey<*>> = listOf(
        DialogueMessageHandler.Key
    )

    override suspend fun querySessions(): List<DialogueSession> {
        val sessions = dialogueDetailRepo.getDialogueSessionsByModelId(modelId)
        if (sessions.isEmpty()) return emptyList()

        val latestSession = sessions[0]
        val messages = dialogueDetailRepo.getDialogueMessagesBySessionId(latestSession.sessionId)
        return sessions.toMutableList().apply {
            this[0] = latestSession.copy(
                messages = messages.toMutableList()
            )
        }
    }

    override fun createSession(): DialogueSession {
        val session =  DialogueSession(
            sessionId = UUID.randomUUID().toString(),
            participants = listOf(
                IMIdentity(id = myUid.uid, type = IdentityType.USER),
                IMIdentity(id = peerId, type = IdentityType.AI)
            ),
            messages = mutableListOf()
        )
        sessionScope.launch {
            dialogueDetailRepo.insertDialogueSession(session)
        }
        return session
    }

    override fun addOrModifyMessage(message: IMMessage<*>) {
        super.addOrModifyMessage(message)
        if (message is DialogueMessage) {
            sessionScope.launch {
                dialogueDetailRepo.insertDialogueMessage(message)
            }
        }
    }

    companion object {
        private const val TAG = "DialogueSessionController"
    }
}