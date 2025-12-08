package mx.edu.utez.gofit.model

import android.provider.ContactsContract

data class RegisterRequest(
    private val email: String,
    private val password: String
)