package me.kolotilov.groupproject.domain.models

/**
 * Статистика по траффику.
 */
data class TrafficStats(
        val hour: Int,
        val fiveHours: Int,
        val day: Int,
        val week: Int
)