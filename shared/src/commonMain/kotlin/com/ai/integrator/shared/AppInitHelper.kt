package com.ai.integrator.shared

import com.ai.integrator.core.datastore.DataStoreModule
import com.ai.integrator.feature.dialogue.DialogueModule
import com.ai.integrator.feature.dialogue.PlatformModule
import org.koin.core.context.startKoin

fun initKoinAndApp() {
    startKoin {  }
    initApp()
}

fun initApp() {
    initApplicationModules()
}

private fun initApplicationModules() {
    DataStoreModule().init()
    PlatformModule().init()
    DialogueModule().init()
}