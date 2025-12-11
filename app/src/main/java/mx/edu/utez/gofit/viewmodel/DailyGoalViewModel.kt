package mx.edu.utez.gofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.model.DailyGoalResponse
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.model.UpdateDailyGoalRequest
import mx.edu.utez.gofit.repository.DailyGoalsRepository
import mx.edu.utez.gofit.repository.RunSessionsRepository
import java.time.LocalDate

class DailyGoalViewModel(
    private val dailyGoalsRepository: DailyGoalsRepository,
    private val runSessionsRepository: RunSessionsRepository
): ViewModel() {

    var uiState by mutableStateOf(DailyGoalUiState())
        private set

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            val goal = dailyGoalsRepository.getDailyGoal()
            val sessions = runSessionsRepository.getRunSessions()

            val totalDistanceToday = sessions
                .filter { LocalDate.parse(it.startedAt.substring(0, 10)) == LocalDate.now() }
                .sumOf { it.distanceMeters }

            uiState = uiState.copy(
                isLoading = false,
                goal = goal,
                sessions = sessions,
                todayProgress = totalDistanceToday.toFloat(),
                completion = (totalDistanceToday.toFloat() / goal.distanceMeter.toFloat()).coerceIn(0f, 1f)
            )
        }
    }

    fun updateGoal(newGoal: Float) {
        viewModelScope.launch {
            uiState = uiState.copy(isUpdating = true)

            dailyGoalsRepository.updateDailyGoal(UpdateDailyGoalRequest(newGoal))
            loadData()

            uiState = uiState.copy(isUpdating = false)
        }
    }
}

data class DailyGoalUiState(
    val isLoading: Boolean = false,
    val isUpdating: Boolean = false,
    val goal: DailyGoalResponse? = null,
    val sessions: List<RunSessionResponse> = emptyList(),
    val todayProgress: Float = 0f,
    val completion: Float = 0f
)