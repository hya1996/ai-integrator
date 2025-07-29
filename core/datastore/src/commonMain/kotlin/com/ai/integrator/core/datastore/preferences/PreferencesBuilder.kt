package com.ai.integrator.core.datastore.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

typealias PreferencesBuilder = (String) -> DataStore<Preferences>