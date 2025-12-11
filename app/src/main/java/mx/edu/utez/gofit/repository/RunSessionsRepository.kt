package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.model.RegisterRunSessionRequest
import mx.edu.utez.gofit.model.RunSessionResponse
import mx.edu.utez.gofit.network.RunSessionsApi
import java.time.LocalDateTime

class RunSessionsRepository(private val api: RunSessionsApi) {

    suspend fun getRunSessions(): List<RunSessionResponse> = api.getRunSessions()

    suspend fun sendRunSession(steps: Int, started: LocalDateTime, ended: LocalDateTime) {
        val request = RegisterRunSessionRequest(
            steps = steps,
            startedAt = started,
            endedAt = ended
        )
        api.postRunSession(request)
    }

    suspend fun deleteRunSession(id: Long) = api.deleteRunSession(id)
}
