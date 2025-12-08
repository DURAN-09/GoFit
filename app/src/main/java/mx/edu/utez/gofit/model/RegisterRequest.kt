package mx.edu.utez.gofit.model

import android.provider.ContactsContract

data class RegisterRequest(
    val email: String,
    val password: String
)