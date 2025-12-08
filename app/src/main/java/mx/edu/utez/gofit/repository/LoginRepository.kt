package mx.edu.utez.gofit.repository

import android.R.attr.password
import mx.edu.utez.gofit.model.LoginRequest
import mx.edu.utez.gofit.network.RetrofitClient

class LoginRepository {
    private val api = RetrofitClient.apiService

    suspend fun login(email: String,password: String): LoginResponse {
        return api.login(LoginRequest(email, password))
    }
}