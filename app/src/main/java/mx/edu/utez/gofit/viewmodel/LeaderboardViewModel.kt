package mx.edu.utez.gofit.viewmodel

import androidx.lifecycle.ViewModel
import mx.edu.utez.gofit.repository.LeaderboardRepository

class LeaderboardViewModel(
    private val leaderboardRepository: LeaderboardRepository
): ViewModel() {
}