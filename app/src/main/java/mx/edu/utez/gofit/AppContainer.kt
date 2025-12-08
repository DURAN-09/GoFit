package mx.edu.utez.gofit

import android.content.Context
import mx.edu.utez.gofit.data.UserPreferences
import mx.edu.utez.gofit.network.RetrofitClient
import mx.edu.utez.gofit.repository.AuthRepository
import mx.edu.utez.gofit.repository.GoalsRepository
import mx.edu.utez.gofit.repository.LeaderboardRepository
import mx.edu.utez.gofit.repository.RunSessionsRepository
import mx.edu.utez.gofit.viewmodel.factory.AuthViewModelFactory

class AppContainer(context: Context) {

    val userPreferences = UserPreferences(context)

    val retrofitClient = RetrofitClient(userPreferences)

    val authRepository = AuthRepository(retrofitClient.authApi, userPreferences)
    val goalsRepository = GoalsRepository(retrofitClient.goalsApi)
    val leaderboardRepository = LeaderboardRepository(retrofitClient.leaderboardApi)
    val runSessionsRepository = RunSessionsRepository(retrofitClient.runSessionsApi)

    val authViewModelFactory = AuthViewModelFactory(authRepository)
}

