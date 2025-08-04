package com.ai.integrator.feature.dialogue

import com.ai.integrator.core.framework.application.ApplicationModule
import com.ai.integrator.data.platform.di.platformDataDIModule
import com.ai.integrator.feature.dialogue.di.platformFeatureDIModule
import org.koin.core.context.loadKoinModules

class PlatformModule : ApplicationModule() {
    override fun onInit() {
        loadKoinModules(listOf(platformDataDIModule, platformFeatureDIModule))
    }
}