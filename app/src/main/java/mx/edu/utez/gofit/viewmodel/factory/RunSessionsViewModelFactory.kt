package mx.edu.utez.gofit.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mx.edu.utez.gofit.repository.DailyGoalsRepository
import mx.edu.utez.gofit.repository.RunSessionsRepository
import mx.edu.utez.gofit.viewmodel.RunSessionsViewModel

class RunSessionsViewModelFactory(
    private val repo: RunSessionsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RunSessionsViewModel::class.java)) {
            return RunSessionsViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

