package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class RegisterRunSessionRequest(
    val steps: Int,
    @SerializedName("started_at") val startedAt: LocalDateTime,
    @SerializedName("ended_at") val endedAt: LocalDateTime
)
