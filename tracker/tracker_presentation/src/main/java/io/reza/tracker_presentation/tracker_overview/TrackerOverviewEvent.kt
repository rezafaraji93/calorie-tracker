package io.reza.tracker_presentation.tracker_overview

import io.reza.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    object OnNextDayClicked : TrackerOverviewEvent()
    object OnPreviousDayClicked : TrackerOverviewEvent()
    data class OnToggleMealClicked(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood) : TrackerOverviewEvent()
}
