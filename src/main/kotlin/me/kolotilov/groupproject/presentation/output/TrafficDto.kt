package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Traffic
import java.util.*

@ApiModel("TrafficDto: Пользовательский траффик.")
data class TrafficDto(
        @ApiModelProperty("Дата.")
        @JsonProperty("date")
        val date: Date,

        @ApiModelProperty("Траффик в МБ.")
        @JsonProperty( "amount")
        val amount: Int,

        @ApiModelProperty("ID.")
        @JsonProperty("id")
        val id: Int = 0
)

fun Traffic.toTrafficDto() = TrafficDto(
        date = date.toDate(),
        amount = amount,
        id = id
)