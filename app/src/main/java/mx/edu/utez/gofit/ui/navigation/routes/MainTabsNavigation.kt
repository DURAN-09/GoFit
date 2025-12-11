package mx.edu.utez.gofit.ui.navigation.routes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.edu.utez.gofit.AppContainer
import mx.edu.utez.gofit.ui.navigation.routes.main.MainRoutes
import mx.edu.utez.gofit.ui.screens.maintabs.DailyGoalScreen
import mx.edu.utez.gofit.ui.screens.maintabs.Home
import mx.edu.utez.gofit.ui.screens.maintabs.LeaderboardScreen

@Composable
fun MainTabsNavigation(
    appContainer: AppContainer
) {
    val tabController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(tabController)
        }
    ) { padding ->
        NavHost(
            navController = tabController,
            startDestination = MainRoutes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(MainRoutes.HOME) { Home(
                runVM = viewModel(factory = appContainer.runSessionsViewModelFactory),
                accelVM = viewModel(factory = appContainer.accelerometerViewModelFactory)
            )}
            composable(MainRoutes.DAILY_GOAL) {
                DailyGoalScreen(
                    viewModel(factory = appContainer.dailyGoalViewModelFactory)
                )
            }
            composable(MainRoutes.LEADERBOARD) {
                LeaderboardScreen(
                    viewModel(factory = appContainer.leaderboardViewModelFactory)
                )
            }
        }
    }
}
