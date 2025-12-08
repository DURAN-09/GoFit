package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class RegisterRunSessionRequest(
    private val steps: Int,
    @SerializedName("started_at") private val startedAt: LocalDateTime,
    @SerializedName("ended_at") private val endedAt: LocalDateTime
)
