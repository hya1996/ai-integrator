@file:OptIn(ExperimentalTime::class)

package com.ai.integrator.data.dialogue.model

import com.ai.integrator.im.identity.IMIdentity
import com.ai.integrator.im.session.IMSession
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class DialogueSession(
    override val sessionId: String,
    override val participants: List<IMIdentity>,
    override var lastActiveTs: Long = Clock.System.now().toEpochMilliseconds(),
) : IMSession