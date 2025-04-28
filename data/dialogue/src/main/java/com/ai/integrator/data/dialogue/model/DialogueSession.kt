package com.ai.integrator.data.dialogue.model

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.message.IMMessage
import com.ai.integrator.im.session.IMSession

data class DialogueSession(
    override val sessionId: String,
    override val participantIds: List<IMIdentity>,
    override val messages: MutableList<IMMessage<*>>,
    override var lastActiveTime: Long = System.currentTimeMillis()
) : IMSession<DialogueSession> {
    override fun copySession(): DialogueSession {
        return this.copy()
    }
}