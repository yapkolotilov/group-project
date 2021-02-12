package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Tariff
import javax.validation.constraints.Pattern

/**
 * Редактирование данных тарифов.
 *
 */
data class EditTariffDto(
        @JsonProperty("mac")
        @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\\$", message = "Неправильный формат Mac-адреса")
        val mac: String? = null,
        @JsonProperty("ip")
        @Pattern(regexp = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|\$)){4}\\b", message = "Неправильный формат ip-адреса")
        val ip: String? = null
)

fun Tariff.apply(editTariffDto: EditTariffDto) = copy(
        mac = editTariffDto.mac ?: mac,
        ip = editTariffDto.ip ?: ip
)