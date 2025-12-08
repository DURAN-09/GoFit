package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class DailyGoalResponse(
    private val id: Long,
    @SerializedName("distance")
    private val distanceMeter: Number,
    @SerializedName("created_at")
    private val createdAt: LocalDateTime,
    @SerializedName("updated_at")
    private val updatedAt: LocalDateTime

)
