package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.TrafficStats

@ApiModel("TrafficStatsDto: Статистика по пользовательскому траффику (мб).")
data class TrafficStatsDto(
        @ApiModelProperty("Траффик за последний час.")
        @JsonProperty("hour")
        val hour: Int,

        @ApiModelProperty("Траффик за последние 5 часов.")
        @JsonProperty("fiveHours")
        val fiveHours: Int,

        @ApiModelProperty("Траффик за последний день.")
        @JsonProperty("day")
        val day: Int,

        @ApiModelProperty("Траффик за последнюю неделю.")
        @JsonProperty("week")
        val week: Int
)

fun TrafficStats.toTrafficStatsDto() = TrafficStatsDto(
        hour = hour,
        fiveHours = fiveHours,
        day = day,
        week = week
)