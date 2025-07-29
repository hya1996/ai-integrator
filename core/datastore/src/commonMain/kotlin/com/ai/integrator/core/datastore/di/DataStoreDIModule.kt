package com.ai.integrator.core.datastore.di

import com.ai.integrator.core.datastore.preferences.PreferencesBuilder
import com.ai.integrator.core.datastore.preferences.prefs.ApiKeyPref
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val platformDIModule: Module

val dataStoreDIModule = module {
    includes(platformDIModule)

    single<ApiKeyPref> {
        ApiKeyPref(
            dataStore = get<PreferencesBuilder>().invoke(ApiKeyPref.API_KEY_PREF_FILE_NAME)
        )
    }
}