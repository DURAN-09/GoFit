package mx.edu.utez.gofit.network

import mx.edu.utez.gofit.data.UserPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java


class RetrofitClient(
    private val userPreferences: UserPreferences
) {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPreferences))
            .build()
    }


    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://gofit-api.fly.dev")
            .addConverterFactory(GsonConverterFactory.create())
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