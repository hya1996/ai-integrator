package com.ai.integrator.core.datastore.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.ai.integrator.core.datastore.preferences.PreferencesBuilder
import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import okio.Path.Companion.toPath
import org.koin.dsl.module

internal actual val platformDIModule = module {
    single<PreferencesBuilder> {{ preferencesFileName ->
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { ApiKeyPref.API_KEY_PREF_FILE_NAME.toPath() }
        )
    }}
}