package mx.edu.utez.gofit.ui.screens.maintabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.utez.gofit.ui.theme.GoFitTheme
import mx.edu.utez.gofit.viewmodel.LeaderboardViewModel

@Composable
fun LeaderboardScreen(
     vm: LeaderboardViewModel
) {
}

@Composable
fun LeaderboardScreen(){

}

@Preview(showBackground = true)
@Composable
fun LeaderboardScreenPreview(){
    GoFitTheme {
        LeaderboardScreen()
    }
}