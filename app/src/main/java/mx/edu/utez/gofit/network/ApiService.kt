package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.model.LoginRequest
import mx.edu.utez.gofit.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //pendiente
    @POST("/login")
    suspend fun login(
        @Body request : LoginRequest
    ): LoginResponse

    /*
    @GET("users")
    suspend fun getUsers(): List<User>
    */
}