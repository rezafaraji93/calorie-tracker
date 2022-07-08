package io.reza.tracker_domain.use_case

import io.reza.tracker_domain.model.TrackedFood
import io.reza.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        return repository.deleteTrackedFood(trackedFood)
    }


}