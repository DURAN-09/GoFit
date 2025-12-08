package mx.edu.utez.gofit.ui.screens.authstack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import mx.edu.utez.gofit.viewmodel.AuthState
import mx.edu.utez.gofit.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    uiState: AuthState,
    register: (String, String) -> Unit,
    onRegisterSuccess: () -> Unit
) {

    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

       TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        Spacer(Modifier.height(12.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { register(email, password) },
            enabled = !uiState.loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (uiState.loading) "Creando..." else "Registrarse")
        }

        if (uiState.error != null) {
            Text(uiState.error!!, color = Color.Red)
        }

        if (uiState.success) {
            LaunchedEffect(Unit) { onRegisterSuccess() }
        }
    }
}

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    onRegisterSuccess: () -> Unit
) {
    RegisterScreen(
        uiState = viewModel.uiState,
        register = viewModel::register,
        onRegisterSuccess = onRegisterSuccess
    )
}

@Preview(showBackground = true)
@Composable
fun IdleRegisterScreenPreview() {
    GoFitTheme {
        RegisterScreen(
            uiState = AuthState(
                loading = false,
                success = false,
                error = null),
            register = { _, _ -> },
            onRegisterSuccess = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingRegisterScreenPreview() {
    GoFitTheme {
        RegisterScreen(
            uiState = AuthState(
                loading = true,
                success = false,
                error = null),
            register = { _, _ -> },
            onRegisterSuccess = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorRegisterScreenPreview() {
    GoFitTheme {
        RegisterScreen(
            uiState = AuthState(
                loading = false,
                success = false,
                error = "Error de autenticación"),
            register = { _, _ -> },
            onRegisterSuccess = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SuccessRegisterScreenPreview() {
    GoFitTheme {
        RegisterScreen(
            uiState = AuthState(
                loading = false,
                success = true,
                error = null),
            register = { _, _ -> },
            onRegisterSuccess = {}
        )
    }
}