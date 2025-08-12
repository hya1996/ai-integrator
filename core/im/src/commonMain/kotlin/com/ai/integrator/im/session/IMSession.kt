package com.ai.integrator.im.session

import com.ai.integrator.im.identity.IMIdentity

interface IMSession {
    val sessionId: String
    val participants: List<IMIdentity>
    var lastActiveTs: Long
}