package com.ai.integrator.data.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ai.integrator.data.model.database.dao.ModelDao
import com.ai.integrator.data.model.model.ModelInfo
import com.ai.integrator.data.model.model.toModelEntity
import com.ai.integrator.data.model.model.toModelInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ModelRepository(
    private val modelDao: ModelDao
) {
    suspend fun getModelInfo(modelId: Long): ModelInfo? {
        return modelDao.getModel(modelId)?.toModelInfo()
    }

    fun getModelInfoPagingDataFlow(): Flow<PagingData<ModelInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5
            )
        ) {
            modelDao.getModelPagingSource()
        }
            .flow
            .map { data -> data.map { it.toModelInfo() } }
    }

    suspend fun addModel(modelInfo: ModelInfo) {
        modelDao.insertModels(listOf(modelInfo.toModelEntity()))
    }

    suspend fun deleteModel(modelId: Long) {
        modelDao.deleteModels(listOf(modelId))
    }
}