package com.ai.integrator.im.identity

import kotlinx.serialization.Serializable

@Serializable
data class IMIdentity(
    val id: Long,
    val type: IdentityType
)
