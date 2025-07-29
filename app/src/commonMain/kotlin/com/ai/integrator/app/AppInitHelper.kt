package com.ai.integrator.app

import com.ai.integrator.core.datastore.DataStoreModule
import com.ai.integrator.feature.dialogue.DialogueModule

fun initApp() {
    initApplicationModules()
}

private fun initApplicationModules() {
    DataStoreModule().init()
    DialogueModule().init()
}