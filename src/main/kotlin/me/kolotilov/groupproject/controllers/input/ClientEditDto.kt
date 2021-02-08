package me.kolotilov.groupproject.controllers.input

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client
import javax.validation.constraints.Positive

/**
 * Редактирование данных о клиенте.
 *
 * @param enabled Включать ли юзера.
 * @param balance Новый баланс.
 */
data class ClientEditDto(
        @JsonProperty("enabled")
        val enabled: Boolean? = null,
        @JsonProperty("balance")
        @Positive(message = "Должен быть положительным")
        val balance: Int? = null
)

fun ClientEditDto.toClient(client: Client): Client {
    return client.copy(
            enabled = enabled ?: client.enabled,
            balance = balance ?: client.balance
    )
}