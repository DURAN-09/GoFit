package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import mx.edu.utez.gofit.model.LeaderboardItemResponse

@Composable
fun LeaderboardDistanceList(items: List<LeaderboardItemResponse>) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            LeaderboardCard(
                rank = index + 1,
                title = "User ${item.userId}",
                subtitle = "Steps: ${item.steps}",
                highlight = index == 0
            )
        }
    }
}
