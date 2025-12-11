package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.data.RunSession
import mx.edu.utez.gofit.data.RunSessionRequest
import mx.edu.utez.gofit.network.RunSessionsApi

class RunSessionsRepository(private val api: RunSessionsApi) {

    suspend fun getRunSessions(): List<RunSession> = api.getRunSessions()

    suspend fun sendRunSession(steps: Int, started: String, ended: String) {
        val request = RunSessionRequest(
            steps = steps,
            started_at = started,
            ended_at = ended
        )
        api.postRunSession(request)
    }
}
