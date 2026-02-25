package com.ai.integrator.feature.dialogue.di

import com.ai.integrator.data.dialogue.repository.DialogueMessageRepository
import com.ai.integrator.data.model.repository.ModelRepository
import com.ai.integrator.feature.dialogue.screen.message.DialogueMessageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dialogueFeatureDIModule = module {
    viewModel { params -> DialogueMessageViewModel(params.get(), get<ModelRepository>(), get<DialogueMessageRepository>()) }
}