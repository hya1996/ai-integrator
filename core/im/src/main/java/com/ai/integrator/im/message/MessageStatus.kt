package com.ai.integrator.im.message

import kotlinx.serialization.Serializable

@Serializable
enum class MessageStatus {
    SENDING,
    SEND_SUCCESS,
    SEND_FAIL
}