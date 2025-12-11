package mx.edu.utez.gofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.model.AverageDistanceResponse
import mx.edu.utez.gofit.model.LeaderboardItemResponse
import mx.edu.utez.gofit.repository.LeaderboardRepository

class LeaderboardViewModel(
    private val repository: LeaderboardRepository
): ViewModel() {
    var uiState by mutableStateOf(LeaderboardUiState())
        private set

    init {
        loadDistance()
        loadAverage()
    }

    fun loadDistance() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val result = repository.getLongestRunsLeaderboard()
                .sortedByDescending { it.steps }

            uiState = uiState.copy(
                longestRuns = result,
                isLoading = false
            )
        }
    }

    fun loadAverage() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val result = repository.getAverageRunsLeaderboard()
                .sortedByDescending { it.averageDistance }

            uiState = uiState.copy(
                averageRuns = result,
                isLoading = false
            )
        }
    }

    fun selectTab(tab: LeaderboardTab) {
        uiState = uiState.copy(selectedTab = tab)
    }
}



data class LeaderboardUiState(
    val isLoading: Boolean = false,
    val selectedTab: LeaderboardTab = LeaderboardTab.Distance,
    val longestRuns: List<LeaderboardItemResponse> = emptyList(),
    val averageRuns: List<AverageDistanceResponse> = emptyList()
)

enum class LeaderboardTab { Distance, Average}