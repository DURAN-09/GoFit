package mx.edu.utez.gofit.network

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import mx.edu.utez.gofit.data.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = runBlocking {
            userPreferences.getToken().first()
        }

        val originalRequest = chain.request()

        if (token.isNullOrEmpty()) {
            return chain.proceed(originalRequest)
        }

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}
