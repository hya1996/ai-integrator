package com.ai.integrator.data.dialogue.model

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.session.IMSession

data class DialogueSession(
    override val sessionId: String,
    override val participants: List<IMIdentity>,
    override var lastActiveTime: Long = System.currentTimeMillis(),
) : IMSession