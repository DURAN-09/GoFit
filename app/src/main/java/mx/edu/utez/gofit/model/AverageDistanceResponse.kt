package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName

data class AverageDistanceResponse(
    val id: Long,
    val email: String,
    @SerializedName("avg_distance") val averageDistance: Double
)
