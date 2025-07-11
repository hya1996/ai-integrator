package com.ai.integrator.core.framework.log

import co.touchlab.kermit.Logger

object Log {
    fun v(tag: String, message: String) {
        Logger.v(tag) { message }
    }

    fun d(tag: String, message: String) {
        Logger.d(tag) { message }
    }

    fun i(tag: String, message: String) {
        Logger.i(tag) { message }
    }

    fun w(tag: String, message: String) {
        Logger.w(tag) { message }
    }

    fun e(tag: String, message: String) {
        Logger.e(tag) { message }
    }
}