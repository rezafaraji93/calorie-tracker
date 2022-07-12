package io.reza.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.reza.core.domain.models.ActivityLevel
import io.reza.core.domain.models.Gender
import io.reza.core.domain.models.GoalType
import io.reza.core.domain.models.UserInfo
import io.reza.core.domain.preferences.Preferences
import io.reza.tracker_domain.model.MealType
import io.reza.tracker_domain.model.TrackedFood
import org.junit.Before
import org.junit.Test
import org.threeten.bp.Clock
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.util.*
import kotlin.random.Random

class CalculateMelaNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 25,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "launch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.MIN,
                calories = Random.nextInt(2000)
            )
        }
        val result = calculateMealNutrients(trackedFoods)
        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = trackedFoods
            .filter { it.mealType == MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

}