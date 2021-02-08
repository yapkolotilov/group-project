package me.kolotilov.groupproject.controllers.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.TrafficStats

/**
 * Статистика по пользовательскому траффику (мб).
 *
 * @param hour Траффик за последний час.
 * @param fiveHours Траффик за последние 5 часов.
 * @param day Траффик за последний день.
 * @param week Траффик за последнюю неделю.
 */
data class TrafficStatsDto(
        @JsonProperty("hour")
        val hour: Int,
        @JsonProperty("fiveHours")
        val fiveHours: Int,
        @JsonProperty("day")
        val day: Int,
        @JsonProperty("week")
        val week: Int
)

fun TrafficStats.toTrafficStatsDto() = TrafficStatsDto(
        hour, fiveHours, day, week
)