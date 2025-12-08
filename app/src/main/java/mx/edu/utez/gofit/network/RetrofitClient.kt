package mx.edu.utez.gofit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java


object RetrofitClient {
    private const val BASE_URL="https://gofit-api.fly.dev"

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApi: AuthApi by lazy {
        retrofit.create(AuthApi::class.java)
    }

    val goalsApi: GoalsApi by lazy {
        retrofit.create(GoalsApi::class.java)
    }

    val leaderboardApi: LeaderboardApi by lazy {
        retrofit.create(LeaderboardApi::class.java)
    }

    val runSessionsApi: RunSessionsApi by lazy {
        retrofit.create(RunSessionsApi::class.java)
    }
}