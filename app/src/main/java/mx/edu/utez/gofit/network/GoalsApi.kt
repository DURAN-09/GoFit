package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.model.DailyGoalResponse
import mx.edu.utez.gofit.model.UpdateDailyGoalRequest
import retrofit2.http.GET
import retrofit2.http.PUT

interface GoalsApi {

    @GET("daily_goals")
    suspend fun getDailyGoal(): DailyGoalResponse

    @PUT("daily_goals")
    suspend fun updateDailyGoal(request: UpdateDailyGoalRequest): DailyGoalResponse
}