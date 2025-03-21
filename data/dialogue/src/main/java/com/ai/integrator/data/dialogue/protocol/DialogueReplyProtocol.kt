package com.ai.integrator.data.dialogue.protocol

import com.ai.integrator.data.dialogue.model.DialogueMessage

internal data class DialogueReplyReq(
    val model: String,
    val messages: List<DialogueMessage>,
    val stream: Boolean = true,
    val tools: List<Any> = emptyList(),
    val responseFormat: ResponseFormat = ResponseFormat(type = "text"),
    val stop: String? = null,
)

internal data class ResponseFormat(
    val type: String
)

internal data class DialogueReplyResp(
    val choices: List<Choice>
)

internal data class Choice(
    val delta: DialogueMessage
)

