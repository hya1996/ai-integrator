package com.ai.integrator.core.datastore.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.ai.integrator.core.datastore.preferences.PreferencesBuilder
import okio.Path.Companion.toPath
import org.koin.dsl.module

internal actual val platformDIModule = module {
    single<PreferencesBuilder> {{ preferencesFileName ->
        val filePath = get<Context>().filesDir.resolve(preferencesFileName).absolutePath.toPath()
        PreferenceDataStoreFactory.createWithPath(
            produceFile = { filePath }
        )
    }}
}