package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import mx.edu.utez.gofit.model.AverageDistanceResponse

@Composable
fun LeaderboardAverageList(items: List<AverageDistanceResponse>) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            LeaderboardCard(
                rank = index + 1,
                title = item.email,
                subtitle = "Promedio: ${"%.2f".format(item.averageDistance)} m",
                highlight = index == 0
            )
        }
    }
}
