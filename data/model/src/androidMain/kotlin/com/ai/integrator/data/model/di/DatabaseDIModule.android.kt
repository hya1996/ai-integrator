package com.ai.integrator.data.model.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.model.database.ModelDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<ModelDatabase>>(named("model_db_builder")) {
        getModelDatabaseBuilder(get())
    }
}

private fun getModelDatabaseBuilder(ctx: Context): RoomDatabase.Builder<ModelDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("model-database.db")
    return Room.databaseBuilder<ModelDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}