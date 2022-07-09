package io.reza.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.reza.tracker_presentation.R
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun parseDateText(date: LocalDate): String {

    val today = LocalDate.now(ZoneId.systemDefault())
    return when (date) {
        today -> stringResource(id = io.reza.core.R.string.today)
        today.minusDays(1) -> stringResource(id = io.reza.core.R.string.yesterday)
        today.plusDays(1) -> stringResource(id = io.reza.core.R.string.tomorrow)
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }


}