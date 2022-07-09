package io.reza.tracker_domain.use_case

import io.reza.tracker_domain.model.TrackedFood
import io.reza.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }


}