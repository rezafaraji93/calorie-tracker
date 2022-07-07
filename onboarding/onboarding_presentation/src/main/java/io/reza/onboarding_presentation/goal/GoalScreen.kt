package io.reza.onboarding_presentation.goal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import io.reza.core.domain.models.GoalType
import io.reza.core.util.UiEvent
import io.reza.core_ui.LocalSpacing
import io.reza.onboarding_presentation.components.ActionButton
import io.reza.onboarding_presentation.components.SelectableButton

@Composable
fun GoalScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = io.reza.core.R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyRow {
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.lose),
                        selected = viewModel.selectedGoalType is GoalType.LoseWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onGoalTypeClicked(GoalType.LoseWeight)
                        },
                        textStyle = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                }
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.keep),
                        selected = viewModel.selectedGoalType is GoalType.KeepWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onGoalTypeClicked(GoalType.KeepWeight)
                        },
                        textStyle = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                }
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.gain),
                        selected = viewModel.selectedGoalType is GoalType.GainWeight,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onGoalTypeClicked(GoalType.GainWeight)
                        },
                        textStyle = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
        ActionButton(
            text = stringResource(id = io.reza.core.R.string.next),
            onClick = viewModel::onNextClicked,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}