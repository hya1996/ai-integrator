package com.ai.integrator.data.dialogue.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<DialogueDatabase>>(named("dialogue_db_builder")) {
        getDialogueDatabaseBuilder(get())
    }
}

private fun getDialogueDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DialogueDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("dialogue-database.db")
    return Room.databaseBuilder<DialogueDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}