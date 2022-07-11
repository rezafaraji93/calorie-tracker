package io.reza.onboarding_presentation.activity

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
import io.reza.core.domain.models.ActivityLevel
import io.reza.core.util.UiEvent
import io.reza.core_ui.LocalSpacing
import io.reza.onboarding_presentation.components.ActionButton
import io.reza.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityScreen(
    onNextClick: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
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
                text = stringResource(id = io.reza.core.R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            LazyRow {
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.low),
                        selected = viewModel.selectedActivityLevel is ActivityLevel.Low,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onActivityLevelClicked(ActivityLevel.Low)
                        },
                        textStyle = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                }
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.medium),
                        selected = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onActivityLevelClicked(ActivityLevel.Medium)
                        },
                        textStyle = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Normal
                        )
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceMedium))
                }
                item {
                    SelectableButton(
                        text = stringResource(id = io.reza.core.R.string.high),
                        selected = viewModel.selectedActivityLevel is ActivityLevel.High,
                        color = MaterialTheme.colors.primaryVariant,
                        selectedTextColor = Color.White,
                        onClick = {
                            viewModel.onActivityLevelClicked(ActivityLevel.High)
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