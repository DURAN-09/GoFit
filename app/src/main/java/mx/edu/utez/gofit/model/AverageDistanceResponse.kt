package mx.edu.utez.gofit.model

import com.google.gson.annotations.SerializedName

data class AverageDistanceResponse(
    private val id: Long,
    private val email: String,
    @SerializedName("avg_distance") private val averageDistance: Double
)
