package mx.edu.utez.gofit.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.edu.utez.gofit.ui.theme.GoFitTheme

@Composable
fun EditGoalDialog(
    currentGoal: Float,
    onDismiss: () -> Unit,
    onSave: (Float) -> Unit
) {
    var newGoal by remember { mutableStateOf(currentGoal) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar meta diaria") },
        text = {
            Column {
                Text("Ingresa tu nueva meta en metros:")
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = newGoal.toString(),
                    onValueChange = { newGoal = it.toFloatOrNull() ?: newGoal },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onSave(newGoal) }
            ) {
                Text("Guardar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun EditGoalDialogPreview(){
    GoFitTheme {
        EditGoalDialog(
            currentGoal = 2f,
            onDismiss = {},
            onSave = {}
        )
    }
}
