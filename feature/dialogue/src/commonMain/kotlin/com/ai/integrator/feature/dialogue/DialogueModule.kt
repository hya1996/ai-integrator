package com.ai.integrator.feature.dialogue

import com.ai.integrator.core.framework.application.ApplicationModule
import com.ai.integrator.data.dialogue.di.dialogueDataDIModule
import com.ai.integrator.feature.dialogue.di.dialogueFeatureDIModule
import org.koin.core.context.loadKoinModules

class DialogueModule : ApplicationModule() {
    override fun onInit() {
        loadKoinModules(listOf(dialogueDataDIModule, dialogueFeatureDIModule))
    }
}