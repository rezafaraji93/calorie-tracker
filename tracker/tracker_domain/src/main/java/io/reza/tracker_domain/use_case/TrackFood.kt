package io.reza.tracker_domain.use_case

import io.reza.tracker_domain.model.MealType
import io.reza.tracker_domain.model.TrackableFood
import io.reza.tracker_domain.model.TrackedFood
import io.reza.tracker_domain.repository.TrackerRepository
import org.threeten.bp.LocalDate
import kotlin.math.roundToInt

class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {

        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                amount = amount,
                mealType = mealType,
                date = date,
                carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((food.proteinsPer100g / 100f) * amount).roundToInt(),
                fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
                imageUrl = food.imageUrl,
                calories = ((food.caloriesPer100g / 100f) * amount).roundToInt()
            )
        )
    }


}