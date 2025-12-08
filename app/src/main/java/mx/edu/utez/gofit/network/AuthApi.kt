package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.model.AuthResponse
import mx.edu.utez.gofit.model.LoginRequest
import mx.edu.utez.gofit.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse
}