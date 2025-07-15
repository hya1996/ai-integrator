package com.ai.integrator.data.dialogue.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import org.koin.dsl.module
import java.io.File

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<DialogueDatabase>> {
        getDialogueDatabaseBuilder()
    }
}

private fun getDialogueDatabaseBuilder(): RoomDatabase.Builder<DialogueDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), "dialogue-database.db")
    return Room.databaseBuilder<DialogueDatabase>(
        name = dbFile.absolutePath
    )
}