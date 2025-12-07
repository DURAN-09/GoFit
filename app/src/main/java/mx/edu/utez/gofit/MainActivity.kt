package mx.edu.utez.gofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import mx.edu.utez.gofit.ui.navigation.RootNavigation
import mx.edu.utez.gofit.ui.theme.GoFitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoFitTheme {
                setContent {
                    RootNavigation()
                }
                    /*Login(
                        modifier = Modifier.padding(innerPadding)
                    )*/
            }
        }
    }
}