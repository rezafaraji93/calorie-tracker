package io.reza.calorietracker.navigation.util

import androidx.navigation.NavController
import io.reza.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}