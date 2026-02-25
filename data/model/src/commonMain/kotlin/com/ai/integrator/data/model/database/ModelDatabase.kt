package com.ai.integrator.data.model.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.data.model.database.dao.ModelDao
import com.ai.integrator.data.model.database.entity.ModelEntity

@Database(
    entities = [
        ModelEntity::class
    ],
    version = 1
)
@ConstructedBy(ModelDatabaseConstructor::class)
abstract class ModelDatabase : RoomDatabase() {
    abstract fun modelDao(): ModelDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ModelDatabaseConstructor : RoomDatabaseConstructor<ModelDatabase> {
    override fun initialize(): ModelDatabase
}

fun getModelDatabase(
    builder: RoomDatabase.Builder<ModelDatabase>
): ModelDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(AppDispatcher.IO)
        .build()
}