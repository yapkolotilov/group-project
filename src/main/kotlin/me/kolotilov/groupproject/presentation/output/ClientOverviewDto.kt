package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client

/**
 * Модель для списка клиентов.
 *
 * @param name ФИО.
 * @param id ID.
 */
data class ClientOverviewDto(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("id")
        val id: Int
)

fun Client.toClientOverview() = ClientOverviewDto(
        name = name,
        id = id
)