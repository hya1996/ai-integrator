package com.ai.integrator.data.dialogue.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.dao.DialogueSessionDao
import com.ai.integrator.data.dialogue.database.entity.DialogueMessageEntity
import com.ai.integrator.data.dialogue.database.entity.DialogueSessionEntity
import com.ai.integrator.im.identity.IMIdentityConverter

@Database(
    entities = [
        DialogueMessageEntity::class,
        DialogueSessionEntity::class
    ],
    version = 1
)
@TypeConverters(
    IMIdentityConverter::class
)
@ConstructedBy(DialogueDatabaseConstructor::class)
abstract class DialogueDatabase : RoomDatabase() {
    abstract fun dialogueMessageDao(): DialogueMessageDao

    abstract fun dialogueSessionDao(): DialogueSessionDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DialogueDatabaseConstructor : RoomDatabaseConstructor<DialogueDatabase> {
    override fun initialize(): DialogueDatabase
}

fun getDialogueDatabase(
    builder: RoomDatabase.Builder<DialogueDatabase>
): DialogueDatabase {
    return builder
        .addMigrations()
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(AppDispatcher.IO)
        .build()
}