package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel("LoginDto: Авторизация.")
data class LoginDto(
    @ApiModelProperty("Имя пользователя.")
    @JsonProperty("username")
    val username: String,

    @ApiModelProperty("Пароль.")
    @JsonProperty("password")
    val password: String
)
