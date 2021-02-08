package me.kolotilov.groupproject.controllers.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Client
import java.util.*

/**
 * Полная информация по клиенту.
 *
 * @param name ФИО.
 * @param balance Баланс лицевого счёта.
 * @param enabled Подключён ли клиент.
 * @param tariff Тариф.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param phone Телефон.
 * @param email Электронная почта.
 * @param mac MAC-адрес.
 * @param loans Кредиты.
 * @param ip IP-адрес.
 */
data class ClientDetailsDto(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("balance")
        val balance: Int,
        @JsonProperty("enabled")
        val enabled: Boolean,
        @JsonProperty("phone")
        val phone: String,
        @JsonProperty("email")
        val email: String,
        @JsonProperty( "registeredAt")
        val registeredAt: Date,
        @JsonProperty("tariffs")
        val tariffs: List<TariffDto>,
        @JsonProperty("loans")
        val loans: List<LoanDto>,
        @JsonProperty("id")
        val id: Int
)

fun Client.toClientDetailsDto() = ClientDetailsDto(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDate(),
        tariffs = tariffs.map { it.toTariffDto() },
        loans = loans.map { it.toLoanDto() },
        id = id
)
