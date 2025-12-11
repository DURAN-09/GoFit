package mx.edu.utez.gofit.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import mx.edu.utez.gofit.data.RunSession
import mx.edu.utez.gofit.data.RunSessionRequest

interface RunSessionsApi {
    @GET("/run_sessions")
    suspend fun getRunSessions(): List<RunSession>

    @POST("/run_sessions")
    suspend fun postRunSession(@Body request: RunSessionRequest): RunSession
}
