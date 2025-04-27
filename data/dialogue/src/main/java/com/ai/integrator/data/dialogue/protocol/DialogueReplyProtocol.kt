package com.ai.integrator.data.dialogue.protocol

import com.ai.integrator.data.dialogue.model.DialogueMessageContent
import kotlinx.serialization.Serializable

@Serializable
internal data class DialogueReplyReq(
    val model: String,
    val messages: List<DialogueMessageContent>,
    val stream: Boolean = true,
    val responseFormat: ResponseFormat = ResponseFormat(type = "text"),
    val stop: String? = null,
)

@Serializable
internal data class ResponseFormat(
    val type: String
)

@Serializable
internal data class DialogueReplyResp(
    val choices: List<Choice>
)

@Serializable
internal data class Choice(
    val delta: DialogueMessageContent
)

