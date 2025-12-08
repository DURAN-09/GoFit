package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.model.AverageDistanceResponse
import mx.edu.utez.gofit.model.LeaderboardItemResponse
import retrofit2.http.GET

interface LeaderboardApi {
    @GET("/leaderboard/longest")
    suspend fun getLongestRunsLeaderboard(): List<LeaderboardItemResponse>

    @GET("/leaderboard/averages")
    suspend fun getAverageDistanceLeaderboard(): List<AverageDistanceResponse>

}