package io.reza.tracker_presentation.search

import io.reza.tracker_domain.model.MealType
import io.reza.tracker_domain.model.TrackableFood
import org.threeten.bp.LocalDate

sealed class SearchEvent {
    object OnSearch : SearchEvent()
    data class OnQueryChange(val query: String) : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(val food: TrackableFood, val amount: String) : SearchEvent()
    data class OnTrackFoodClicked(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()

    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvent()
}
