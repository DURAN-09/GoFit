package mx.edu.utez.gofit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.gofit.data.RunSession

@Composable
fun RunSessionsTable(sessions: List<RunSession>) {
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
                Text(s.started_at.take(10), modifier = Modifier.weight(1f))
                Text("${s.distance_meters} m", modifier = Modifier.weight(1f))
                Text("${s.steps}", modifier = Modifier.weight(1f))
            }
            Divider()
        }
    }
}
