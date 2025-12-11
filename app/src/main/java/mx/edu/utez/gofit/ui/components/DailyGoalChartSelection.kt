package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.edu.utez.gofit.model.RunSessionResponse
import java.time.LocalDate

@Composable
fun DailyGoalChartSection(sessions: List<RunSessionResponse>) {
    val grouped = sessions.groupBy { it.startedAt.toLocalDate() }
        .mapValues { it.value.sumOf { s -> s.distanceMeters } }

    val week = (0..6).map { LocalDate.now().minusDays(it.toLong()) }.reversed()

    val entries = week.map { date -> grouped[date] ?: 0.0 }

    Column(Modifier.fillMaxWidth().padding(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            entries.forEachIndexed { index, distance ->
                Box(
                    modifier = Modifier
                        .height((distance / 10).coerceAtMost(200.0).dp)
                        .width(22.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(6.dp))
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            week.forEach {
                Text(it.dayOfWeek.toString().take(3))
            }
        }
    }
}
