package com.ai.integrator.data.model.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.model.database.ModelDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<ModelDatabase>>(named("model_db_builder")) {
        getModelDatabaseBuilder()
    }
}

private fun getModelDatabaseBuilder(): RoomDatabase.Builder<ModelDatabase> {
    val dbFilePath = documentDirectory() + "/model-database.db"
    return Room.databaseBuilder<ModelDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}