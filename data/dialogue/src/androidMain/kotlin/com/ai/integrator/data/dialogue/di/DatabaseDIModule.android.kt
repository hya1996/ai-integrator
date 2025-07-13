package com.ai.integrator.data.dialogue.di

import androidx.room.RoomDatabase
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import com.ai.integrator.data.dialogue.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun databaseModule() = module {
    single<RoomDatabase.Builder<DialogueDatabase>> {
        getDatabaseBuilder(get())
    }
}