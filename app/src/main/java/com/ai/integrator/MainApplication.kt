package com.ai.integrator

import com.ai.integrator.core.framework.application.BaseApplication
import com.ai.integrator.core.framework.util.AppUtils
import com.ai.integrator.data.dialogue.di.dialogueDataModule
import com.ai.integrator.feature.dialogue.di.dialogueFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        AppUtils.init(this)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                dialogueDataModule,
                dialogueFeatureModule
            )
        }
    }
}