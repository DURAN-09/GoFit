package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.network.RetrofitClient

class DailyGoalsRepository(
    private val client: RetrofitClient
) {
    suspend fun getDailyGoals() = client.goalsApi.getDailyGoals()
}
