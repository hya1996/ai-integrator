package com.ai.integrator.data.model.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ai.integrator.data.model.database.entity.ModelEntity

@Dao
interface ModelDao {
    @Query("""
        SELECT * FROM model
        WHERE model_id = :modelId
    """)
    suspend fun getModel(modelId: Long): ModelEntity?

    @Query("""
        SELECT * FROM model
    """)
    fun getModelPagingSource(): PagingSource<Int, ModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModels(models: List<ModelEntity>)

    @Query("""
        DELETE FROM model
        WHERE model_id IN (:modelIds)
    """)
    suspend fun deleteModels(modelIds: List<Long>)
}