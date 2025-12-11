package mx.edu.utez.gofit.ui.screens.maintabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.gofit.model.AverageDistanceResponse
import mx.edu.utez.gofit.model.LeaderboardItemResponse
import mx.edu.utez.gofit.ui.components.LeaderboardAverageList
import mx.edu.utez.gofit.ui.components.LeaderboardDistanceList
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import mx.edu.utez.gofit.viewmodel.LeaderboardTab
import mx.edu.utez.gofit.viewmodel.LeaderboardUiState
import mx.edu.utez.gofit.viewmodel.LeaderboardViewModel
import java.time.temporal.ChronoUnit

@Composable
fun LeaderboardScreen(
     vm: LeaderboardViewModel
) {

    val state = vm.uiState
    LeaderboardScreen(
        state = state,
        selectTab = vm::selectTab
    )

}

@Composable
fun LeaderboardScreen(
    state: LeaderboardUiState,
    selectTab: (LeaderboardTab) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Leaderboard",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        TabRow(selectedTabIndex = state.selectedTab.ordinal) {
            Tab(
                selected = state.selectedTab == LeaderboardTab.Distance,
                onClick = { selectTab(LeaderboardTab.Distance) },
                text = { Text("Por Distancia") }
            )
            Tab(
                selected = state.selectedTab == LeaderboardTab.Average,
                onClick = { selectTab(LeaderboardTab.Average) },
                text = { Text("Por Promedio") }
            )
        }

        Spacer(Modifier.height(20.dp))

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        } else {
            when (state.selectedTab) {
                LeaderboardTab.Distance ->
                    LeaderboardDistanceList(items = state.longestRuns)

                LeaderboardTab.Average ->
                    LeaderboardAverageList(items = state.averageRuns)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LeaderboardScreenDistancePreview(){
    GoFitTheme {
        LeaderboardScreen(
            state = LeaderboardUiState(
                longestRuns = listOf(
                    LeaderboardItemResponse(
                        id = 1,
                        userId = 1,
                        steps = 1000,
                        startedAt = java.time.LocalDateTime.now(),
                        endedAt = java.time.LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                        createdAt = java.time.LocalDateTime.now(),
                        updatedAt = java.time.LocalDateTime.now()
                    ),
                    LeaderboardItemResponse(
                        id = 1,
                        userId = 1,
                        steps = 700,
                        startedAt = java.time.LocalDateTime.now(),
                        endedAt = java.time.LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                        createdAt = java.time.LocalDateTime.now(),
                        updatedAt = java.time.LocalDateTime.now()
                    )
                )
            ),
            selectTab = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LeaderboardScreenAveragePreview(){
    GoFitTheme {
        LeaderboardScreen(
            state = LeaderboardUiState(
                selectedTab = LeaderboardTab.Average,
                averageRuns = listOf(
                    AverageDistanceResponse(
                        id = 1,
                        email = "test@example.com",
                        averageDistance = 1000.0
                    ),
                    AverageDistanceResponse(
                        id = 1,
                        email = "user@example.com",
                        averageDistance = 700.0
                    )
                )
            ),
            selectTab = {}
        )
    }
}