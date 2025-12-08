package mx.edu.utez.gofit.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("user_prefs")

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    fun getToken(): Flow<String?> =
        context.dataStore.data.map { it[TOKEN_KEY] }
}
