package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun RunSessionsTable(
    sessions: List<RunSessionResponse>,
    onDelete: (Long) -> Unit
) {
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
            Text("Acción", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        }

        Divider(Modifier.padding(vertical = 8.dp))

        sessions.forEach { s ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = s.startedAt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                    modifier = Modifier.weight(1f)
                )
                Text("${s.distanceMeters}", modifier = Modifier.weight(1f))
                Text("${s.steps}", modifier = Modifier.weight(1f))

                IconButton(
                    onClick = {
                        try {
                            onDelete(s.id)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    },
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Red, shape = CircleShape)
                        .padding(horizontal = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = Color.White
                    )
                }
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunSessionsTablePreview() {
    GoFitTheme {
        RunSessionsTable(
            sessions = listOf(
                RunSessionResponse(
                    id = 1,
                    userId = 1,
                    steps = 1000,
                    distanceMeters = 1000.0,
                    startedAt = java.time.LocalDateTime.now().toString(),
                    endedAt = java.time.LocalDateTime.now().plus(1, ChronoUnit.HOURS).toString(),
                    createdAt = java.time.LocalDateTime.now().toString(),
                    updatedAt = java.time.LocalDateTime.now().toString(),
                    durationSeconds = 3600.0
                )
            ),
            onDelete = {}
        )
    }
}

