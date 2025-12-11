package mx.edu.utez.gofit.repository

import mx.edu.utez.gofit.model.LeaderboardItemResponse
import mx.edu.utez.gofit.network.LeaderboardApi

class LeaderboardRepository(val leaderboardApi: LeaderboardApi) {

    suspend fun getLongestRunsLeaderboard() = leaderboardApi.getLongestRunsLeaderboard()
    suspend fun getAverageRunsLeaderboard() = leaderboardApi.getAverageDistanceLeaderboard()
}
