package com.ai.integrator.feature.dialogue.di

import com.ai.integrator.feature.dialogue.dialog.apikey.PlatformApiKeyViewModel
import com.ai.integrator.feature.dialogue.screen.model.PlatformModelViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val platformFeatureDIModule = module {
    viewModelOf(::PlatformModelViewModel)
    viewModelOf(::PlatformApiKeyViewModel)
}