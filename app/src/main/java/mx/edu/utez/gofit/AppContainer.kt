package mx.edu.utez.gofit

import android.content.Context
import mx.edu.utez.gofit.data.UserPreferences
import mx.edu.utez.gofit.network.RetrofitClient
import mx.edu.utez.gofit.repository.AuthRepository
import mx.edu.utez.gofit.repository.DailyGoalsRepository
import mx.edu.utez.gofit.repository.LeaderboardRepository
import mx.edu.utez.gofit.repository.RunSessionsRepository
import mx.edu.utez.gofit.viewmodel.factory.AccelerometerViewModelFactory
import mx.edu.utez.gofit.viewmodel.factory.AuthViewModelFactory
import mx.edu.utez.gofit.viewmodel.factory.DailyGoalViewModelFactory
import mx.edu.utez.gofit.viewmodel.factory.LeaderboardViewModelFactory
import mx.edu.utez.gofit.viewmodel.factory.RunSessionsViewModelFactory

class AppContainer(context: Context) {

    val userPreferences = UserPreferences(context)

    val retrofitClient = RetrofitClient(userPreferences)

    val authRepository = AuthRepository(retrofitClient.authApi, userPreferences)
    val leaderboardRepository = LeaderboardRepository(retrofitClient.leaderboardApi)
    val runSessionsRepository = RunSessionsRepository(retrofitClient.runSessionsApi)
    val dailyGoalsRepository = DailyGoalsRepository(retrofitClient.goalsApi)
    val authViewModelFactory = AuthViewModelFactory(authRepository)
    val dailyGoalViewModelFactory = DailyGoalViewModelFactory(dailyGoalsRepository, runSessionsRepository)
    val runSessionsViewModelFactory = RunSessionsViewModelFactory(runSessionsRepository)
    val accelerometerViewModelFactory = AccelerometerViewModelFactory(context)
    val leaderboardViewModelFactory = LeaderboardViewModelFactory(leaderboardRepository)
}

