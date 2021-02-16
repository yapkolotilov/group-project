package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("TokenDto: Токен.")
data class TokenDto(
    @ApiModelProperty("Токен.")
    @JsonProperty("token")
    val token: String
)
