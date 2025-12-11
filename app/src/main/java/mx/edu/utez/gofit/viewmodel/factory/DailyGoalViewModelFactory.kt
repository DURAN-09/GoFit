package mx.edu.utez.gofit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.gofit.repository.DailyGoalsRepository
import mx.edu.utez.gofit.repository.RunSessionsRepository
import mx.edu.utez.gofit.viewmodel.AuthViewModel
import mx.edu.utez.gofit.viewmodel.DailyGoalViewModel

class DailyGoalViewModelFactory(
    private val dailyGoalsRepository: DailyGoalsRepository,
    private val runSessionsRepository: RunSessionsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyGoalViewModel::class.java)) {
            return DailyGoalViewModel(dailyGoalsRepository, runSessionsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}