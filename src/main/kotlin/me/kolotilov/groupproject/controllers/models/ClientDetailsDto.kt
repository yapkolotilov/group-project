package me.kolotilov.groupproject.controllers.models

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client
import java.util.*

/**
 * Полная информация по клиенту.
 */
data class ClientDetailsDto(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("balance")
        val balance: Int,
        @JsonProperty("enabled")
        val enabled: Boolean,
        @JsonProperty("tariff")
        val tariff: String,
        @JsonProperty("contractName")
        val contractName: String,
        @JsonProperty("owner")
        val owner: String,
        @JsonProperty("phone")
        val phone: String,
        @JsonProperty("email")
        val email: String,
        @JsonProperty("mac")
        val mac: String,
        @JsonProperty("ip")
        val ip: String,
        @JsonProperty("traffic")
        val traffic: List<TrafficDto>,
        @JsonProperty("registeredAt")
        val registeredAt: Date,
        @JsonProperty("lastPaymentAt")
        val lastPaymentAt: Date,
        @JsonProperty("id")
        val id: Int = 0
)

fun Client.toClientDetails() = ClientDetailsDto(
        name, balance, enabled, tariff, contractName, owner, phone, email, mac, ip, traffic.map { it.toTrafficDto() },
        registeredAt.toDate(), lastPaymentAt.toDate(), id
)
