package io.reza.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.reza.calorietracker.navigation.util.navigate
import io.reza.calorietracker.ui.theme.CalorieTrackerTheme
import io.reza.core.navigation.Route
import io.reza.onboarding_presentation.activity.ActivityScreen
import io.reza.onboarding_presentation.goal.GoalScreen
import io.reza.onboarding_presentation.age.AgeScreen
import io.reza.onboarding_presentation.gender.GenderScreen
import io.reza.onboarding_presentation.height.HeightScreen
import io.reza.onboarding_presentation.weight.WeightScreen
import io.reza.onboarding_presentation.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {

                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.TRACKER_OVERVIEW) {

                        }
                        composable(Route.SEARCH) {

                        }
                    }
                }
            }
        }
    }
}