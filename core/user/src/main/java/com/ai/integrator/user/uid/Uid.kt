package com.ai.integrator.user.uid

val myUid = Uid(1L)

data class Uid(
    val uid: Long
) {
    val isValid: Boolean
        get() = uid > 0

    val isMe: Boolean
        get() = this == myUid
}
