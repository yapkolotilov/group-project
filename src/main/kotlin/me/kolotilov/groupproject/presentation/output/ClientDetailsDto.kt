package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.database.models.TrafficEntity
import me.kolotilov.groupproject.database.models.toTrafficEntity
import me.kolotilov.groupproject.domain.models.Client
import java.util.*

/**
 * Полная информация по клиенту.
 *
 * @param name ФИО.
 * @param balance Баланс лицевого счёта.
 * @param enabled Подключён ли клиент.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param phone Телефон.
 * @param email Электронная почта.
 * @param mac MAC-адрес.
 * @param loans Кредиты.
 * @param ip IP-адрес.
 */
@ApiModel("ClientDetailsDto: Полная информация по клиенту.")
data class ClientDetailsDto(
        @ApiModelProperty("ФИО.")
        @JsonProperty("name")
        val name: String,

        @ApiModelProperty("Баланс лицевого счёта.")
        @JsonProperty("balance")
        val balance: Int,

        @ApiModelProperty("Подключён ли клиент.")
        @JsonProperty("enabled")
        val enabled: Boolean,

        @ApiModelProperty("Телефон.")
        @JsonProperty("phone")
        val phone: String,

        @ApiModelProperty("e-mail.")
        @JsonProperty("email")
        val email: String,

        @ApiModelProperty("Дата регистрации.")
        @JsonProperty( "registeredAt")
        val registeredAt: Date,

        @ApiModelProperty("Кредиты.")
        @JsonProperty("loans")
        val loans: List<LoanDto>,

        @ApiModelProperty("Название договора.")
        @JsonProperty("contract_name")
        val contractName: String,

        @ApiModelProperty("Данные контракта.")
        @JsonProperty("contract_data")
        val contractData: String?,

        @ApiModelProperty("На кого зарегистрирован договор.")
        @JsonProperty("owner")
        val owner: String,

        @ApiModelProperty("MAC-адрес.")
        @JsonProperty("mac")
        val mac: String,

        @ApiModelProperty("ip-адрес.")
        @JsonProperty("ip")
        val ip: String,

        @ApiModelProperty("Дата последнего платежа.")
        @JsonProperty("last_payment_at")
        val lastPaymentAt: Date,

        @ApiModelProperty("Трафик.")
        @JsonProperty("traffic")
        val traffic: List<TrafficEntity>,

        @ApiModelProperty("Номер контракта")
        @JsonProperty("contract_number")
        val contractNumber: Int,

        @ApiModelProperty("Тариф")
        @JsonProperty("tariff")
        val tariff: TariffDto
)

fun Client.toClientDetailsDto() = ClientDetailsDto(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDate(),
        loans = loans.map { it.toLoanDto() },
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        ip = ip,
        mac = mac,
        lastPaymentAt = lastPaymentAt.toDate(),
        traffic = traffic.map { it.toTrafficEntity() },
        contractNumber = contractNumber,
        tariff = tariff.toTariffDto()
)
