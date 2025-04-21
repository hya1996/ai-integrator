package com.ai.integrator.data.dialogue.model

import kotlinx.serialization.Serializable

@Serializable
data class DialogueModelInfo(
    val modelId: Long,
    val simpleName: String,
    val modelName: String,
    val iconUrl: String,
    val intro: String = "",
)
