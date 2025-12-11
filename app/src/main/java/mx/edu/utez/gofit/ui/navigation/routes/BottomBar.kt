package mx.edu.utez.gofit.ui.navigation.routes

import android.net.http.SslCertificate.restoreState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import mx.edu.utez.gofit.ui.navigation.routes.main.MainRoutes
@Composable
fun BottomBar(navController: NavHostController) {

    val items = listOf(
        MainRoutes.HOME,
        MainRoutes.DAILY_GOAL,
        MainRoutes.LEADERBOARD
    )

    NavigationBar {
        items.forEach { route ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == route,
                onClick = {
                    navController.navigate(route) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector =
                            when (route) {
                                MainRoutes.HOME -> Icons.Default.Home
                                MainRoutes.DAILY_GOAL -> Icons.Default.Flag
                                else -> Icons.Default.Star
                            },
                        contentDescription = null
                    )
                }
            )
        }
    }
}
