package com.ai.integrator.data.dialogue.di

import com.ai.integrator.data.dialogue.database.DialogueDatabase
import com.ai.integrator.data.dialogue.database.dao.DialogueMessageDao
import com.ai.integrator.data.dialogue.database.dao.DialogueSessionDao
import com.ai.integrator.data.dialogue.database.getDialogueDatabase
import com.ai.integrator.data.dialogue.datasource.DialogueMessageRemoteDataSource
import com.ai.integrator.data.dialogue.repository.DialogueMessageRepository
import com.ai.integrator.data.dialogue.repository.DialogueSessionRepository
import com.ai.integrator.data.dialogue.session.DialogueMessageHandler
import com.ai.integrator.data.dialogue.session.DialogueSessionController
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal expect val platformDIModule: Module

val dialogueDataDIModule = module {
    includes(platformDIModule)

    single<DialogueDatabase> { getDialogueDatabase(get(named("dialogue_db_builder"))) }
    single<DialogueMessageDao> { get<DialogueDatabase>().dialogueMessageDao() }
    single<DialogueSessionDao> { get<DialogueDatabase>().dialogueSessionDao() }
    singleOf(::DialogueMessageRemoteDataSource)
    singleOf(::DialogueMessageRepository)
    singleOf(::DialogueSessionRepository)

    factory { params -> DialogueSessionController(params.get<Long>(), get(), get()) }
    factory { params -> DialogueMessageHandler(params.get<CoroutineScope>(), get()) }
}