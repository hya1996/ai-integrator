package com.ai.integrator.core.datastore.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.ai.integrator.core.datastore.preferences.PreferencesBuilder
import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
internal actual val platformDIModule = module {
    single<PreferencesBuilder> {{ preferencesFileName ->
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val pathStr = (requireNotNull(documentDirectory).path + "/${ApiKeyPref.API_KEY_PREF_FILE_NAME}")
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { pathStr.toPath() }
        )
    }}
}