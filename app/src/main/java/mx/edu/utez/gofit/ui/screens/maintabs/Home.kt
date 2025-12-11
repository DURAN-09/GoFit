package mx.edu.utez.gofit.ui.screens.maintabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.ui.components.RunSessionsTable
import mx.edu.utez.gofit.viewmodel.AccelerometerViewModel
import mx.edu.utez.gofit.viewmodel.RunSessionsViewModel
import java.time.LocalDateTime

@Composable
fun Home(
    accelVM: AccelerometerViewModel,
    runVM: RunSessionsViewModel

) {
    val sessions by runVM.sessions.collectAsState()
    val steps by accelVM.steps.observeAsState(0)
    val meters by accelVM.meters.observeAsState(0f)
    Home(
        sessions = sessions,
        steps = steps,
        meters = meters,
        start = accelVM::start,
        stop = accelVM::stop,
        sendSession = runVM::sendSession
    )
}

@Composable
fun Home(
    sessions: List<RunSessionResponse>,
    steps: Int,
    meters: Float,
    start: () -> Unit,
    stop: () -> Unit,
    sendSession: (Int, LocalDateTime, LocalDateTime) -> Unit
){
    var isRunning by remember { mutableStateOf(false) }
    var startedAt by remember { mutableStateOf<LocalDateTime>(LocalDateTime.now()) }

    LaunchedEffect(isRunning) {
        if (isRunning) start() else stop()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .background(Color(0xFF0A84FF).copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${"%.2f".format(meters)} m", fontSize = 32.sp, color = Color.Black)
                Spacer(Modifier.height(8.dp))
                Text("$steps pasos", fontSize = 22.sp, color = Color.DarkGray)
            }
        }

        FloatingActionButton(
            onClick = {
                if (!isRunning) {
                    startedAt = LocalDateTime.now()
                    isRunning = true
                } else {
                    val endedAt = LocalDateTime.now()
                    sendSession(steps, startedAt, endedAt)
                    isRunning = false
                }
            },
            containerColor = if (!isRunning) Color(0xFF0A84FF) else Color.Red,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = if (!isRunning) Icons.Filled.PlayArrow else Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }

        RunSessionsTable(sessions)
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    GoFitTheme {
        Home(
            sessions = emptyList(),
            steps = 0,
            meters = 0f,
            start = {},
            stop = {},
            sendSession = { _, _, _ -> }
        )
    }
}