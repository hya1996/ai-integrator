package com.ai.integrator.data.dialogue.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.entity.DialogueMessageEntity
import com.ai.integrator.im.identity.IMIdentityConverter

@Database(
    entities = [
        DialogueMessageEntity::class
    ],
    version = 1,
)
@TypeConverters(
    IMIdentityConverter::class
)
internal abstract class DialogueDatabase : RoomDatabase() {
    abstract fun dialogueMessageDao(): DialogueMessageDao
}