package io.reza.tracker_domain.use_case

data class TrackerUseCases(
    val calculateMealNutrients: CalculateMealNutrients,
    val deleteTrackedFood: DeleteTrackedFood,
    val getFoodsForData: GetFoodsForData,
    val searchFood: SearchFood,
    val trackFood: TrackFood
)
