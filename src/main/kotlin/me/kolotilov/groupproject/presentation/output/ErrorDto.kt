package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorDto(
    @JsonProperty("error")
    val error: String
)
