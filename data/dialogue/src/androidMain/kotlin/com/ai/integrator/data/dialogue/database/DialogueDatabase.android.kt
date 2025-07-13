package com.ai.integrator.data.dialogue.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<DialogueDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("dialogue-database.db")
    return Room.databaseBuilder<DialogueDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}