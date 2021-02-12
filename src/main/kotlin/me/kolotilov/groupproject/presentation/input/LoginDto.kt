package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginDto(
    @JsonProperty("username")
    val username: String,
    @JsonProperty("password")
    val password: String
)
