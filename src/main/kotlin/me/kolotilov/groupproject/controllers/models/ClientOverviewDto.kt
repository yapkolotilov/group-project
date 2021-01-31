package me.kolotilov.groupproject.controllers.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client

/**
 * Модель для списка клиентов.
 *
 * @param name ФИО.
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