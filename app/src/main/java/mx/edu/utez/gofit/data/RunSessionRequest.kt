package mx.edu.utez.gofit.data

data class RunSessionRequest(
    val steps: Int,
    val started_at: String,
    val ended_at: String
)

