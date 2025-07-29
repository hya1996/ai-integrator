package com.ai.integrator.core.datastore.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.ai.integrator.core.framework.coroutine.dispatcher.AppDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BasePref(
    protected val dataStore: DataStore<Preferences>,
    protected val prefScope: CoroutineScope = CoroutineScope(SupervisorJob() + AppDispatcher.IO)
) {
    protected fun <T> Preferences.Key<T>.listenValue(): Flow<T?> {
        return dataStore.data.map { preferences -> preferences[this] }
    }

    protected fun <T> Preferences.Key<T>.listenValue(defValue: T): Flow<T> {
        return dataStore.data.map { preferences -> preferences[this] ?: defValue }
    }

    protected suspend fun <T> Preferences.Key<T>.get(): T? {
        return dataStore.data.map { preferences -> preferences[this] }.first()
    }

    protected fun <T> Preferences.Key<T>.set(value: T) {
        prefScope.launch {
            dataStore.edit { preferences -> preferences[this@set] = value }
        }
    }
}