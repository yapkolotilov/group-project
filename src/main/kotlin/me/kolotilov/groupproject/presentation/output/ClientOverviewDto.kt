package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Client

/**
 * Модель для списка клиентов.
 *
 * @param name ФИО.
 * @param contractNumber ID.
 */
@ApiModel("ClientOverviewDto: Модель для списка клиентов.")
data class ClientOverviewDto(
        @ApiModelProperty("ФИО")
        @JsonProperty("name")
        val name: String,

        @ApiModelProperty("Номер договора.")
        @JsonProperty("contract_number")
        val contractNumber: Int
)

fun Client.toClientOverview() = ClientOverviewDto(
        name = name,
        contractNumber = contractNumber
)