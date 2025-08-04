package com.ai.integrator.data.platform.repository

import com.ai.integrator.data.platform.datasource.PlatformModelLocalDataSource
import com.ai.integrator.data.platform.model.PlatformModelInfo
import kotlinx.coroutines.flow.Flow

class PlatformModelRepository(
    private val platformModelLocalDS: PlatformModelLocalDataSource
) {
    fun getModelList(): Flow<List<PlatformModelInfo>> = platformModelLocalDS.getModelList()

    suspend fun getModelById(id: Long): PlatformModelInfo? = platformModelLocalDS.getModelById(id)
}