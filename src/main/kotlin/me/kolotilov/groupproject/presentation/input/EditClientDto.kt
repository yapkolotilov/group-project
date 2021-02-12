package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client
import javax.validation.constraints.Positive

/**
 * Редактирование данных о клиенте.
 *
 * @param enabled Включать ли юзера.
 * @param balance Новый баланс.
 */
data class EditClientDto(
        @JsonProperty("enabled")
        val enabled: Boolean? = null,
        @JsonProperty("balance")
        @Positive(message = "Должен быть положительным")
        val balance: Int? = null
)

fun Client.apply(editClientDto: EditClientDto) = copy(
    enabled = editClientDto.enabled ?: enabled,
    balance = editClientDto.balance ?: balance
)
