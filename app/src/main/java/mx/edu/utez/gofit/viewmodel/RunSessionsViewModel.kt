package mx.edu.utez.gofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.repository.RunSessionsRepository
import java.time.LocalDateTime

class RunSessionsViewModel(private val repo: RunSessionsRepository) : ViewModel() {

    var sessions = MutableStateFlow<List<RunSessionResponse>>(emptyList())
        private set

    init {
        loadSessions()
    }

    fun loadSessions() {
        viewModelScope.launch {
            try {
                sessions.value = repo.getRunSessions()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteSession(id: Long) {
        viewModelScope.launch {
            repo.deleteRunSession(id)
            loadSessions()
        }
    }

    fun sendSession(steps: Int, started: LocalDateTime, ended: LocalDateTime) {
        viewModelScope.launch {
            try {
                repo.sendRunSession(steps, started, ended)
                loadSessions()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
