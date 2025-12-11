package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.data.RunSession
import retrofit2.http.GET
import retrofit2.http.PUT

interface GoalsApi {

    @GET("daily_goals")
    suspend fun getDailyGoals(): List<RunSession>

    @PUT("daily_goals")
    suspend fun updateDailyGoal(request: Any): Any
}