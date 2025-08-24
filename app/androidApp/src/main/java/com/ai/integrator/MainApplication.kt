package com.ai.integrator

import android.app.Application
import com.ai.integrator.shared.initApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initApp()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
        }
    }
}