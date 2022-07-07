package io.reza.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reza.core.domain.models.GoalType
import io.reza.core.domain.preferences.Preferences
import io.reza.core.navigation.Route
import io.reza.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    var selectedGoalType by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalTypeClicked(goalType: GoalType) {
        selectedGoalType = goalType
    }

    fun onNextClicked() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoalType)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
        }
    }

}