package com.ai.integrator.data.model.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.model.database.ModelDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<ModelDatabase>>(named("model_db_builder")) {
        getModelDatabaseBuilder()
    }
}

private fun getModelDatabaseBuilder(): RoomDatabase.Builder<ModelDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "model-database.db")
    return Room.databaseBuilder<ModelDatabase>(
        name = dbFile.absolutePath
    )
}