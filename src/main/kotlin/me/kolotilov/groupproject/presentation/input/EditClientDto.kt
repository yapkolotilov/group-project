package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Client
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive


@ApiModel("EditClientDto: Редактирование данных о клиенте.")
data class EditClientDto(
        @ApiModelProperty("Включать ли юзера.")
        @JsonProperty("enabled")
        val enabled: Boolean? = null,

        @ApiModelProperty("Новый баланс.")
        @JsonProperty("balance")
        @Positive(message = "Должен быть положительным")
        val balance: Int? = null,

        @ApiModelProperty("Новый mac-адрес.")
        @JsonProperty("mac")
        @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})\\$", message = "Неправильный формат Mac-адреса")
        val mac: String? = null,

        @ApiModelProperty("Новый ip-адрес.")
        @JsonProperty("ip")
        @Pattern(regexp = "\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|\$)){4}\\b", message = "Неправильный формат ip-адреса")
        val ip: String? = null
)

fun Client.apply(editClientDto: EditClientDto) = copy(
    enabled = editClientDto.enabled ?: enabled,
    balance = editClientDto.balance ?: balance,
    mac = editClientDto.mac ?: mac,
    ip = editClientDto.ip ?: ip
)
