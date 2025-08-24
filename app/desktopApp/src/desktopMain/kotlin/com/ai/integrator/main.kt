package com.ai.integrator

import ai_integrator.app.desktopapp.generated.resources.Res
import ai_integrator.app.desktopapp.generated.resources.app_name
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ai.integrator.shared.AIApp
import com.ai.integrator.shared.initKoinAndApp
import org.jetbrains.compose.resources.stringResource

fun main() {
    application {
        initKoinAndApp()

        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name)
        ) {
            AIApp()
        }
    }
}