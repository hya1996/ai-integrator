package com.ai.integrator.core.framework.util

import android.app.Application
import android.content.Context

object AppUtils {
    val context: Context
        get() = if (this::appContext.isInitialized) {
            appContext
        } else {
            throw IllegalAccessException("app context not init")
        }

    private lateinit var appContext: Context

    fun init(application: Application) {
        appContext = application
    }
}