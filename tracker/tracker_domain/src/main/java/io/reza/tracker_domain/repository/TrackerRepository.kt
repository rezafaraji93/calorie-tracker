package io.reza.tracker_domain.repository

import io.reza.tracker_domain.model.TrackableFood
import io.reza.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>

}