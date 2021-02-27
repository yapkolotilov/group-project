package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Tariff

@ApiModel("TariffDto: Тариф.")
data class TariffDto(
    @ApiModelProperty("Название")
    @JsonProperty("name")
    val name: String,
    @ApiModelProperty("Скорость (мб/сек).")
    @JsonProperty("speed")
    val speed: Int,
    @ApiModelProperty("Цена (руб/мес).")
    @JsonProperty("price")
    val price: Int
)

fun Tariff.toTariffDto() = TariffDto(
    name = name,
    speed = speed,
    price = price
)
