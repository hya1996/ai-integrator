package com.ai.integrator.im.message

import kotlinx.serialization.Serializable

@Serializable
enum class MessageStatus {
    NO_STATUS,
    SENDING,
    SEND_SUCCESS,
    SEND_FAIL
}