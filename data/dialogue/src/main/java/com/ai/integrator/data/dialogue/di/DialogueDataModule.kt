package com.ai.integrator.data.dialogue.di

import androidx.room.Room
import com.ai.integrator.core.framework.util.AppUtils
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DialogueDataModule {
    @Provides
    @Singleton
    fun provideDialogueDatabase(): DialogueDatabase {
        return Room.databaseBuilder(
            AppUtils.context,
            DialogueDatabase::class.java,
            "dialogue-database"
        ).build()
    }

    @Provides
    fun provideDialogueMessageDao(
        database: DialogueDatabase
    ): DialogueMessageDao = database.dialogueMessageDao()
}