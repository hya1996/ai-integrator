package com.ai.integrator.im.identity

import kotlinx.serialization.Serializable

@Serializable
enum class IdentityType {
    /** 未知身份 */
    UNKNOWN,

    /** 系统 */
    SYSTEM,

    /** 用户 */
    USER,

    /** AI模型 */
    AI
}

val IMIdentity.isUser: Boolean
    get() = type == IdentityType.USER

val IMIdentity.isAI: Boolean
    get() = type == IdentityType.AI