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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.gofit.model.DailyGoalResponse
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.model.UpdateDailyGoalRequest
import mx.edu.utez.gofit.ui.components.DailyGoalChartSection
import mx.edu.utez.gofit.ui.components.EditGoalDialog
import mx.edu.utez.gofit.ui.components.GoalProgressCircle
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import mx.edu.utez.gofit.viewmodel.DailyGoalUiState
import mx.edu.utez.gofit.viewmodel.DailyGoalViewModel
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAmount
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

@Composable
fun DailyGoalScreen(
    viewModel: DailyGoalViewModel
) {
    val state = viewModel.uiState
    DailyGoalScreen(state, viewModel::updateGoal)
}

@Composable
fun DailyGoalScreen(
    state: DailyGoalUiState,
    updateGoal: (Float) -> Unit
){

    var showEditDialog by remember { mutableStateOf(false) }

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
            onClick = { showEditDialog = true },
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
        if (showEditDialog) {
            EditGoalDialog(
                currentGoal = state.goal?.distanceMeter?.toFloat() ?: 0f,
                onDismiss = { showEditDialog = false },
                onSave = { newGoal ->
                    showEditDialog = false
                    updateGoal(newGoal)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyGoalScreenPreview(){
    GoFitTheme {
        DailyGoalScreen(
            DailyGoalUiState(
                isLoading = false,
                isUpdating = false,
                goal = DailyGoalResponse(
                    id = 1,
                    distanceMeter = 1000,
                    createdAt = java.time.LocalDateTime.now(),
                    updatedAt = java.time.LocalDateTime.now()
                ),
                sessions = listOf(
                    RunSessionResponse(
                        id = 1,
                        steps = 700,
                        distanceMeters = 400.toDouble(),
                        startedAt = java.time.LocalDateTime.now(),
                        endedAt = java.time.LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                        createdAt = java.time.LocalDateTime.now(),
                        updatedAt = java.time.LocalDateTime.now(),
                        userId = 1,
                        durationSeconds = 1000
                    )
                ),
                todayProgress = 500f,
                completion = 0.5f
            ),
            updateGoal = {}
        )
    }
}
