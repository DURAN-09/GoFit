package mx.edu.utez.gofit.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import mx.edu.utez.gofit.model.RegisterRunSessionRequest
import mx.edu.utez.gofit.model.RunSessionResponse
import retrofit2.http.DELETE
import retrofit2.http.Path

interface RunSessionsApi {
    @GET("/run_sessions")
    suspend fun getRunSessions(): List<RunSessionResponse>

    @POST("/run_sessions")
    suspend fun postRunSession(@Body request: RegisterRunSessionRequest): RunSessionResponse

    @DELETE("/run_sessions/{id}")
    suspend fun deleteRunSession(@Path("id") id: Long)
}
