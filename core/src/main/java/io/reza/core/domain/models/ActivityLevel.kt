package io.reza.core.domain.models

sealed class ActivityLevel(val name: String) {
    object Low : ActivityLevel("low")
    object Medium : ActivityLevel("mediu")
    object High : ActivityLevel("high")

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when (name) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium
            }
        }
    }
}
