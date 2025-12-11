package mx.edu.utez.gofit.data

data class RunSession(
    val id: Long,
    val user_id: Long,
    val steps: Int,
    val started_at: String,
    val ended_at: String,
    val distance_meters: Float
)
