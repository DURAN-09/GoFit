package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.gofit.ui.theme.GoFitTheme

@Composable
fun GoalProgressCircle(progress: Float, current: Float, goal: Float) {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(180.dp),
            color = ProgressIndicatorDefaults.circularColor,
            strokeWidth = 12.dp,
            trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
            strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
            )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${"%.1f".format(current)} m",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text("de ${goal.toInt()} m")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalProgressCirclePreview() {
    GoFitTheme {
        GoalProgressCircle(0.5f, 100f, 200f)
    }
}
