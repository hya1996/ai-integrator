package com.ai.integrator

import android.app.Application
import com.ai.integrator.feature.dialogue.DialogueModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initApplicationModules()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
        }
    }

    private fun initApplicationModules() {
        DialogueModule().init()
    }
}