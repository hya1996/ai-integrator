package com.ai.integrator.feature.dialogue.di

import com.ai.integrator.feature.dialogue.screen.detail.DialogueDetailViewModel
import com.ai.integrator.feature.dialogue.screen.home.DialogueHomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dialogueFeatureModule = module {
    viewModelOf(::DialogueHomeViewModel)
    viewModel { params -> DialogueDetailViewModel(params.get(), get()) }
}