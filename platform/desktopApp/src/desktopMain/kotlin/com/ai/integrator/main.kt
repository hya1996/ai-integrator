package com.ai.integrator

import ai_integrator.platform.desktopapp.generated.resources.Res
import ai_integrator.platform.desktopapp.generated.resources.app_name
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ai.integrator.app.AIApp
import com.ai.integrator.app.initApp
import org.jetbrains.compose.resources.stringResource
import org.koin.core.context.startKoin

fun main() {
    application {
        initKoin()
        initApp()

        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name)
        ) {
            AIApp()
        }
    }
}

private fun initKoin() {
    startKoin { }
}