package mx.edu.utez.gofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.data.RunSession
import mx.edu.utez.gofit.repository.RunSessionsRepository

class RunSessionsViewModel(private val repo: RunSessionsRepository) : ViewModel() {

    var sessions = MutableStateFlow<List<RunSession>>(emptyList())
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

    fun sendSession(steps: Int, started: String, ended: String) {
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
