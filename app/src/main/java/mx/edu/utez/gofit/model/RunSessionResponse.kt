package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class RunSessionResponse(
    private val id: Long,
    @SerializedName("user_id") private val userId: Long,
    private val steps: Int,
    @SerializedName("started_at") private val startedAt: LocalDateTime,
    @SerializedName("ended_at") private val endedAt: LocalDateTime,
    @SerializedName("created_at") private val createdAt: LocalDateTime,
    @SerializedName("updated_at") private val updatedAt: LocalDateTime,
    @SerializedName("duration") private val durationSeconds: Long,
    @SerializedName("distance_meters") private val distanceMeters: Double
)
