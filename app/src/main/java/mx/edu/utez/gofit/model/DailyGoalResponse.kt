package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DailyGoalResponse(
    val id: Long,
    @SerializedName("distance")
    val distanceMeter: Number,
    @SerializedName("created_at")
    val createdAt: LocalDateTime,
    @SerializedName("updated_at")
    val updatedAt: LocalDateTime

)
