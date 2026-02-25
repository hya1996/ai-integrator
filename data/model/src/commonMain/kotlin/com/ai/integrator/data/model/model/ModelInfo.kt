package com.ai.integrator.data.model.model

import com.ai.integrator.data.model.database.entity.ModelEntity

data class ModelInfo(
    val modelId: Long = 0,
    val modelName: String,
    val requestUrl: String,
    val apiKey: String
)

fun ModelEntity.toModelInfo(): ModelInfo {
    return ModelInfo(
        modelId = modelId,
        modelName = modelName,
        requestUrl = requestUrl,
        apiKey = apiKey
    )
}

fun ModelInfo.toModelEntity(): ModelEntity {
    return ModelEntity(
        modelId = modelId,
        modelName = modelName,
        requestUrl = requestUrl,
        apiKey = apiKey
    )
}