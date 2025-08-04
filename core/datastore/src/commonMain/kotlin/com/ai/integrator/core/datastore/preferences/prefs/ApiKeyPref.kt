package com.ai.integrator.core.datastore.preferences.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ai.integrator.core.datastore.preferences.BasePref

class ApiKeyPref(
    dataStore: DataStore<Preferences>
) : BasePref(dataStore) {
    private val apiKey = stringPreferencesKey("api_key")
    fun listenApiKey() = apiKey.listenValue("")
    suspend fun getApiKey() = apiKey.get()
    suspend fun setApiKey(value: String) = apiKey.set(value)

    companion object Companion {
        const val API_KEY_PREF_FILE_NAME = "api-key.preferences_pb"
    }
}