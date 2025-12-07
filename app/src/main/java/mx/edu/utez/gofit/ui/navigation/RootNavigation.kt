package mx.edu.utez.gofit.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.gofit.ui.navigation.routes.MainTabsNavigation
import mx.edu.utez.gofit.ui.navigation.routes.RootRoutes
import mx.edu.utez.gofit.ui.navigation.routes.auth.AuthRoutes
import mx.edu.utez.gofit.ui.views.authstack.Login

@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RootRoutes.AUTH
    ) {
        // AUTH STACK
        navigation(
            startDestination = AuthRoutes.LOGIN,
            route = RootRoutes.AUTH
        ) {
            composable(AuthRoutes.LOGIN) {
                Login(
                    onLoginSuccess = {
                        navController.navigate(RootRoutes.MAIN_TABS) {
                            popUpTo(RootRoutes.AUTH) { inclusive = true }
                        }
                    }
                )
            }
        }

        // MAIN TABS
        composable(RootRoutes.MAIN_TABS) {
            MainTabsNavigation()
        }
    }
}
