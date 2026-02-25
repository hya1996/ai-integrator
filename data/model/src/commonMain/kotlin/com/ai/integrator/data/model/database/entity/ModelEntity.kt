package com.ai.integrator.data.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "model")
data class ModelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "model_id")
    val modelId: Long = 0L,

    @ColumnInfo(name = "model_name")
    val modelName: String,

    @ColumnInfo(name = "request_url")
    val requestUrl: String,

    @ColumnInfo(name = "api_key")
    val apiKey: String
)