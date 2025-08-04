package com.ai.integrator.feature.dialogue.di

import com.ai.integrator.data.platform.repository.PlatformModelRepository
import com.ai.integrator.feature.dialogue.screen.detail.DialogueDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dialogueFeatureDIModule = module {
    viewModel { params -> DialogueDetailViewModel(params.get(), get<PlatformModelRepository>()) }
}