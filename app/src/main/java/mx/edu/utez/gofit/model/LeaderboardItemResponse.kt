package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class LeaderboardItemResponse(
    val id: Long,
    @SerializedName("user_id") val userId: Long,
    val steps: Int,
    @SerializedName("started_at") val startedAt: LocalDateTime,
    @SerializedName("ended_at") val endedAt: LocalDateTime,
    @SerializedName("created_at") val createdAt: LocalDateTime,
    @SerializedName("updated_at") val updatedAt: LocalDateTime
)
