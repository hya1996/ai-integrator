package com.ai.integrator.app

import com.ai.integrator.core.datastore.DataStoreModule
import com.ai.integrator.feature.dialogue.DialogueModule
import com.ai.integrator.feature.dialogue.PlatformModule

fun initApp() {
    initApplicationModules()
}

private fun initApplicationModules() {
    DataStoreModule().init()
    PlatformModule().init()
    DialogueModule().init()
}