package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class RunSessionResponse(
    val id: Long,
    @SerializedName("user_id") val userId: Long,
    val steps: Int,
    @SerializedName("started_at") val startedAt: String,
    @SerializedName("ended_at") val endedAt: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("duration") val durationSeconds: Double,
    @SerializedName("distance_meters") val distanceMeters: Double
)
