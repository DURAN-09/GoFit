package mx.edu.utez.gofit.ui.navigation.routes

import android.net.http.SslCertificate.restoreState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
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
        //MainRoutes.PROFILE
    )

    NavigationBar {
        items.forEach { route ->
            NavigationBarItem(
                selected = navController.currentDestination?.route == route,
                onClick = {
                    navController.navigate(route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = { Text(route.substringAfter("/")) }
            )
        }
    }
}
