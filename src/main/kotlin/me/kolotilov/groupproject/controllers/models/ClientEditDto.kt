package me.kolotilov.groupproject.controllers.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client

/**
 * Редактирование данных о клиенте.
 */
data class ClientEditDto(
        @JsonProperty("newMac")
        val newMac: String? = null,
        @JsonProperty("newIp")
        val newIp: String? = null,
        @JsonProperty("newEnabled")
        val newEnabled: Boolean? = null,
        @JsonProperty("newBalance")
        val newBalance: Int? = null
)

fun ClientEditDto.toClient(client: Client): Client {
    return client.copy(
            mac = newMac ?: client.mac,
            ip = newIp ?: client.ip,
            enabled = newEnabled ?: client.enabled,
            balance = newBalance ?: client.balance
    )
}