package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAmount
import kotlin.time.Duration.Companion.hours

@Composable
fun RunSessionsTable(sessions: List<RunSessionResponse>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            "Historial de carreras",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(Modifier.fillMaxWidth()) {
            Text("Fecha", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("Distancia", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("Pasos", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }

        Divider(Modifier.padding(vertical = 8.dp))

        sessions.forEach { s ->
            Row(Modifier.fillMaxWidth()) {
                Text(s.startedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), modifier = Modifier.weight(1f))
                Text("${s.distanceMeters} m", modifier = Modifier.weight(1f))
                Text("${s.steps}", modifier = Modifier.weight(1f))
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunSessionsTablePreview(){
    GoFitTheme {
        RunSessionsTable(
            listOf(
                RunSessionResponse(
                    id = 1,
                    steps = 1000,
                    distanceMeters = 1000.toDouble(),
                    startedAt = java.time.LocalDateTime.now(),
                    endedAt = java.time.LocalDateTime.now().plus(1, ChronoUnit.HOURS),
                    createdAt = java.time.LocalDateTime.now(),
                    updatedAt = java.time.LocalDateTime.now(),
                    userId = 1,
                    durationSeconds = 1000
                )
            )
        )
    }
}
