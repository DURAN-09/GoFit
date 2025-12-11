package mx.edu.utez.gofit.ui.screens.maintabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import mx.edu.utez.gofit.ui.components.DailyGoalChartSection
import mx.edu.utez.gofit.ui.components.GoalProgressCircle
import mx.edu.utez.gofit.viewmodel.DailyGoalViewModel

@Composable
fun DailyGoalScreen(
    viewModel: DailyGoalViewModel
) {
    val state = viewModel.uiState

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Tu Meta Diaria",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        GoalProgressCircle(
            progress = state.completion,
            current = state.todayProgress,
            goal = state.goal?.distanceMeter?.toFloat() ?: 0f
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                //TODO: Open modal
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Editar meta diaria")
        }

        Spacer(Modifier.height(24.dp))

        Text(
            "Progreso de la semana",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        DailyGoalChartSection(sessions = state.sessions)
    }
}
