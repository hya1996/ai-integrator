package com.ai.integrator.data.dialogue.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual val platformDIModule = module {
    single<RoomDatabase.Builder<DialogueDatabase>>(named("dialogue_db_builder")) {
        getDialogueDatabaseBuilder()
    }
}

private fun getDialogueDatabaseBuilder(): RoomDatabase.Builder<DialogueDatabase> {
    val dbFilePath = documentDirectory() + "/dialogue-database.db"
    return Room.databaseBuilder<DialogueDatabase>(
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