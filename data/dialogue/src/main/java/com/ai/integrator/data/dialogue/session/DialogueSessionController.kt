package com.ai.integrator.data.dialogue.session

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.identity.IdentityType
import com.ai.integrator.im.message.HandlerKey
import com.ai.integrator.im.session.IMSession
import com.ai.integrator.im.session.IMSessionController
import com.ai.integrator.user.uid.myUid
import java.util.UUID

class DialogueSessionController(
    modelId: Long
) : IMSessionController(modelId, TAG) {
    override val supportMessageHandlers: List<HandlerKey<*>> = listOf(
        DialogueMessageHandler.Key
    )

    override fun querySessions(): List<IMSession> {
        return emptyList()
    }

    override fun createSession(): IMSession {
        return IMSession(
            sessionId = UUID.randomUUID().toString(),
            participantIds = listOf(
                IMIdentity(id = myUid.uid, type = IdentityType.USER),
                IMIdentity(id = peerId, type = IdentityType.AI)
            ),
            messages = mutableListOf()
        )
    }

    companion object {
        private const val TAG = "DialogueSessionController"
    }
}