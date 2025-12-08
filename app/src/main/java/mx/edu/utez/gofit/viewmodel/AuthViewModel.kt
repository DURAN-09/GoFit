package mx.edu.utez.gofit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.edu.utez.gofit.repository.AuthRepository

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    var uiState by mutableStateOf(AuthState())
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true, error = null)

            val result = repo.login(username, password)

            uiState = if (result.isSuccess) {
                uiState.copy(loading = false, success = true)
            } else {
                uiState.copy(loading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }

    fun register(email: String, password: String,) {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true, error = null)

            val result = repo.register(email, password)

            uiState = if (result.isSuccess) {
                uiState.copy(loading = false, success = true)
            } else {
                uiState.copy(loading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }
}

data class AuthState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
