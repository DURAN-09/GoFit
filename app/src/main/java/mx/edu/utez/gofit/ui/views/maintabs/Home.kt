package mx.edu.utez.gofit.ui.views.maintabs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utez.gofit.ui.theme.GoFitTheme

@Composable
fun Home (){
    Box(){
        Text(text = "hola")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    GoFitTheme {
        Home()
    }
}