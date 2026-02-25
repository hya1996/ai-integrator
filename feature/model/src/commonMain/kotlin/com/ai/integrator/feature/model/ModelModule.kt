package com.ai.integrator.feature.model

import com.ai.integrator.core.framework.application.ApplicationModule
import com.ai.integrator.data.model.di.modelDataDIModule
import com.ai.integrator.feature.model.di.modelFeatureDIModule
import org.koin.core.context.loadKoinModules

class ModelModule : ApplicationModule() {
    override fun onInit() {
        loadKoinModules(listOf(modelDataDIModule, modelFeatureDIModule))
    }
}