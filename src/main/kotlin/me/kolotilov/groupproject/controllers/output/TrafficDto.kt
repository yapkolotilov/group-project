package me.kolotilov.groupproject.controllers.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Traffic
import java.util.*

/**
 * Пользовательский траффик.
 *
 *
 * @param date Дата.
 * @param amount Траффик в МБ.
 * @param id ID.
 */
data class TrafficDto(
        @JsonProperty("date")
        val date: Date,
        @JsonProperty( "amount")
        val amount: Int,
        @JsonProperty("id")
        val id: Int = 0
)

fun Traffic.toTrafficDto() = TrafficDto(
        date.toDate(), amount, id
)