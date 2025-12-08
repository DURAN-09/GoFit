package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.datastore.UserPreferences
import mx.edu.utez.gofit.model.LoginRequest
import mx.edu.utez.gofit.model.RegisterRequest
import mx.edu.utez.gofit.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val prefs: UserPreferences
) {

    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val response = api.login(LoginRequest(email, password))
            prefs.saveToken(response.token)
            Result.success(response.token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<String> {
        return try {
            val response = api.register(RegisterRequest(email, password))
            prefs.saveToken(response.token)
            Result.success(response.token)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
