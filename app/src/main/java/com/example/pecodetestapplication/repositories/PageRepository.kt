package com.example.pecodetestapplication.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PageRepository(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun getParameterByKey(
        key: Preferences.Key<*>
    ) = dataStore.data.map { preferences ->
        preferences[key]
    }
        .first()

    suspend fun <T> updateSettings(
        preferenceKey: Preferences.Key<T>,
        value: T
    ) {
        dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }
}