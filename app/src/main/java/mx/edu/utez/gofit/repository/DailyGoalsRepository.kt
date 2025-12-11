package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.model.UpdateDailyGoalRequest
import mx.edu.utez.gofit.network.GoalsApi

class DailyGoalsRepository(
    private val goalsApi: GoalsApi,
) {
    suspend fun getDailyGoal() = goalsApi.getDailyGoal()

    suspend fun updateDailyGoal(request: UpdateDailyGoalRequest) = goalsApi.updateDailyGoal(request)
}
