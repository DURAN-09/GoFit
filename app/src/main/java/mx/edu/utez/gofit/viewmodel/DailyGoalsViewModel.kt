package mx.edu.utez.gofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import mx.edu.utez.gofit.data.RunSession
import mx.edu.utez.gofit.repository.DailyGoalsRepository

class DailyGoalsViewModel(
    private val repo: DailyGoalsRepository
) : ViewModel() {

    var goals = MutableStateFlow<List<RunSession>>(emptyList())
        private set

    init {
        loadGoals()
    }
    fun reload() {
        loadGoals()
    }

    private fun loadGoals() {
        viewModelScope.launch {
            try {
                goals.value = repo.getDailyGoals()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
