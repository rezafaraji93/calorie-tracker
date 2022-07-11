package io.reza.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import io.reza.calorietracker.ui.theme.CalorieTrackerTheme
import io.reza.core.domain.preferences.Preferences
import io.reza.calorietracker.navigation.Route
import io.reza.onboarding_presentation.activity.ActivityScreen
import io.reza.onboarding_presentation.age.AgeScreen
import io.reza.onboarding_presentation.gender.GenderScreen
import io.reza.onboarding_presentation.goal.GoalScreen
import io.reza.onboarding_presentation.height.HeightScreen
import io.reza.onboarding_presentation.nutrient_goals.NutrientGoalScreen
import io.reza.onboarding_presentation.weight.WeightScreen
import io.reza.onboarding_presentation.welcome.WelcomeScreen
import io.reza.tracker_presentation.search.SearchScreen
import io.reza.tracker_presentation.tracker_overview.TrackerOverviewScreen
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = if (shouldShowOnboarding) Route.WELCOME else Route.TRACKER_OVERVIEW,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNextClick = {
                                    navController.navigate(Route.GENDER)
                                }
                            )
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT)
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(
                                onNextClick = {
                                    navController.navigate(Route.AGE)
                                }
                            )
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                onNextClick = {
                                              navController.navigate(Route.ACTIVITY)
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                },
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(
                                onNextClick = {
                                    navController.navigate(Route.GOAL)
                                }
                            )
                        }
                        composable(Route.GOAL) {
                            GoalScreen(
                                onNextClick = {
                                    navController.navigate(Route.NUTRIENT_GOAL)
                                }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, dayOfMonth, month, year ->
                                    navController.navigate(
                                        route = Route.SEARCH + "/$mealName/$dayOfMonth/$month/$year"
                                    )
                                }
                            )
                        }
                        composable(
                            route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument(name = "mealName") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument(name = "month") {
                                    type = NavType.IntType
                                },
                                navArgument(name = "year") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = navController::popBackStack
                            )
                        }
                    }
                }
            }
        }
    }
}