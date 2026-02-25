package com.ai.integrator.feature.model.di

import com.ai.integrator.feature.model.dialog.addmodel.AddModelViewModel
import com.ai.integrator.feature.model.screen.home.ModelHomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val modelFeatureDIModule = module {
    viewModelOf(::AddModelViewModel)
    viewModelOf(::ModelHomeViewModel)
}