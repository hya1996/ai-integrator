package com.ai.integrator.data.platform.model

import kotlinx.serialization.Serializable

@Serializable
data class PlatformModelInfo(
    val modelId: Long,
    val simpleName: String,
    val modelName: String,
    val iconUrl: String,
    val intro: String = "",
)