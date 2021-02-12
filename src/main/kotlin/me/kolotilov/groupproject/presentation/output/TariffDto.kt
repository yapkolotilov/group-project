package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Tariff
import java.util.*

/**
 * Данные о тарифе.
 *
 * @param name Тариф.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param mac MAC-адрес.
 * @param ip IP-адрес.
 * @param id: ID.
 */
data class TariffDto(
        @JsonProperty("name")
        val name: String,
        @JsonProperty( "contractName")
        val contractName: String,
        @JsonProperty("contractData")
        val contractData: String,
        @JsonProperty("owner")
        val owner: String,
        @JsonProperty("traffic")
        val traffic: List<TrafficDto>,
        @JsonProperty("mac")
        val mac: String,
        @JsonProperty("ip")
        val ip: String,
        @JsonProperty("lastPaymentAt")
        val lastPaymentAt: Date,
        @JsonProperty( "id")
        val id: Int
)

fun Tariff.toTariffDto() = TariffDto(
        name = name,
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        traffic = traffic.map { it.toTrafficDto() },
        mac = mac,
        ip = ip,
        lastPaymentAt = lastPaymentAt.toDate(),
        id = id
)