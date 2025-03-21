package com.ai.integrator.data.dialogue.model

const val DIALOGUE_ROLE_USER = "user"
const val DIALOGUE_ROLE_ASSISTANT = "assistant"
const val DIALOGUE_ROLE_SYSTEM = "system"

data class DialogueMessage(
    val role: String = "",
    val content: String
)
