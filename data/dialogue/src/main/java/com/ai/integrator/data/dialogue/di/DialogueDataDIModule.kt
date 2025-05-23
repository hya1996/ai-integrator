package com.ai.integrator.data.dialogue.di

import androidx.room.Room
import com.ai.integrator.data.dialogue.database.DialogueDatabase
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.dao.DialogueSessionDao
import com.ai.integrator.data.dialogue.datasource.DialogueDetailRemoteDataSource
import com.ai.integrator.data.dialogue.datasource.DialogueModelLocalDataSource
import com.ai.integrator.data.dialogue.repository.DialogueDetailRepository
import com.ai.integrator.data.dialogue.repository.DialogueModelRepository
import com.ai.integrator.data.dialogue.session.DialogueMessageHandler
import com.ai.integrator.data.dialogue.session.DialogueSessionController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dialogueDataDIModule = module {
    single<DialogueDatabase> {
        Room.databaseBuilder(get(), DialogueDatabase::class.java, "dialogue-database").build()
    }
    single<DialogueMessageDao> { get<DialogueDatabase>().dialogueMessageDao() }
    single<DialogueSessionDao> { get<DialogueDatabase>().dialogueSessionDao() }
    singleOf(::DialogueDetailRemoteDataSource)
    singleOf(::DialogueModelLocalDataSource)
    singleOf(::DialogueModelRepository)
    singleOf(::DialogueDetailRepository)

    factory { params -> DialogueSessionController(params.get(), get()) }
    factory { params -> DialogueMessageHandler(params.get(), get()) }
}