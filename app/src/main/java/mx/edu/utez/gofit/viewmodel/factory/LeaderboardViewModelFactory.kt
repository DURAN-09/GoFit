package mx.edu.utez.gofit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.gofit.repository.LeaderboardRepository
import mx.edu.utez.gofit.viewmodel.DailyGoalViewModel
import mx.edu.utez.gofit.viewmodel.LeaderboardViewModel

class LeaderboardViewModelFactory(
    private val leaderboardRepository: LeaderboardRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LeaderboardViewModel::class.java)) {
            return LeaderboardViewModel(leaderboardRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}