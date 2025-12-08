package mx.edu.utez.gofit.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.gofit.AppContainer
import mx.edu.utez.gofit.ui.navigation.routes.MainTabsNavigation
import mx.edu.utez.gofit.ui.navigation.routes.RootRoutes
import mx.edu.utez.gofit.ui.navigation.routes.auth.AuthRoutes
import mx.edu.utez.gofit.ui.screens.authstack.LoginScreen
import mx.edu.utez.gofit.ui.screens.authstack.RegisterScreen

@Composable
fun RootNavigation(container: AppContainer) {
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
                LoginScreen (
                    viewModel = viewModel(factory = container.authViewModelFactory),
                    onLoginSuccess = {
                        navController.navigate(RootRoutes.MAIN_TABS) {
                            popUpTo(RootRoutes.AUTH) { inclusive = true }
                        }
                    },
                    onGoToRegister = {
                        navController.navigate(AuthRoutes.REGISTER)
                    }
                )
            }
            composable(AuthRoutes.REGISTER){
                RegisterScreen(
                    viewModel = viewModel(factory = container.authViewModelFactory),
                    onRegisterSuccess = {
                        navController.navigate(RootRoutes.MAIN_TABS) {
                            popUpTo(RootRoutes.AUTH) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(RootRoutes.MAIN_TABS) {
            MainTabsNavigation()
        }
    }
}
