package io.reza.tracker_domain.use_case

import io.reza.core.domain.models.ActivityLevel
import io.reza.core.domain.models.Gender
import io.reza.core.domain.models.GoalType
import io.reza.core.domain.models.UserInfo
import io.reza.core.domain.preferences.Preferences
import io.reza.tracker_domain.model.MealType
import io.reza.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateMealNutrients(
    private val preferences: Preferences
) {

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {
        val allNutrients = trackedFoods
            .groupBy { it.mealType }
            .mapValues { entry ->
                val mealType = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    protein = foods.sumOf { it.protein },
                    fat = foods.sumOf { it.fat },
                    calories = foods.sumOf { it.calories },
                    mealType = mealType
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val calorieGoal = dailyCalorieRequirement(userInfo)
        val carbsGoal = (calorieGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (calorieGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (calorieGoal * userInfo.fatRatio / 9f).roundToInt()
        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = calorieGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )

    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            Gender.Female -> {
                (665.09f + 13.75 * userInfo.weight + 5f * userInfo.height - 6.75 * userInfo.age).roundToInt()
            }
            Gender.Male -> {
                (66.74f + 13.75 * userInfo.weight + 5f * userInfo.height - 6.75 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCalorieRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            ActivityLevel.High -> 1.4f
            ActivityLevel.Low -> 1.2f
            ActivityLevel.Medium -> 1.3f
        }
        val calorieExtra = when (userInfo.goalType) {
            GoalType.GainWeight -> 500
            GoalType.KeepWeight -> 0
            GoalType.LoseWeight -> -500
        }
        return (bmr(userInfo) * activityFactor + calorieExtra).roundToInt()
    }

    data class MealNutrients(
        val carbs: Int,
        val protein: Int,
        val calories: Int,
        val fat: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val fatGoal: Int,
        val caloriesGoal: Int,
        val totalCarbs: Int,
        val totalProtein: Int,
        val totalFat: Int,
        val totalCalories: Int,
        val mealNutrients: Map<MealType, MealNutrients>,
    )

}