package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenDto(
    @JsonProperty("token")
    val token: String
)
