package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Tariff
import me.kolotilov.groupproject.utils.toDateTime
import org.joda.time.DateTime
import java.util.*

@ApiModel("CreateClientDto: Создание клиента.")
data class CreateClientDto(
    @ApiModelProperty("ФИО.")
    @JsonProperty("name")
    val name: String,

    @ApiModelProperty("Баланс лицевого счёта.")
    @JsonProperty("balance")
    val balance: Int,

    @ApiModelProperty("Телефон.")
    @JsonProperty("phone")
    val phone: String,

    @ApiModelProperty("e-mail.")
    @JsonProperty("email")
    val email: String,

    @ApiModelProperty("Название договора.")
    @JsonProperty("contract_name")
    val contractName: String,

    @ApiModelProperty("Данные контракта.")
    @JsonProperty("contract_data")
    val contractData: String,

    @ApiModelProperty("На кого зарегистрирован договор.")
    @JsonProperty("owner")
    val owner: String,

    @ApiModelProperty("MAC-адрес.")
    @JsonProperty("mac")
    val mac: String,

    @ApiModelProperty("ip-адрес.")
    @JsonProperty("ip")
    val ip: String,

    @ApiModelProperty("Номер контрактаю")
    @JsonProperty("contract_number")
    val contractNumber: Int,

    @ApiModelProperty("Название тарифа")
    @JsonProperty("tariff")
    val tariff: String,

    @ApiModelProperty("Дата регистрации")
    @JsonProperty("registeredAt")
    val registeredAt: Date? = null,

    @ApiModelProperty("lastPaymentAt")
    @JsonProperty("lastPaymentAt")
    val lastPaymentAt: Date? = null
)

fun CreateClientDto.toClient(tariff: Tariff) = Client(
    name = name,
    balance = balance,
    phone = phone,
    email = email,
    contractName = contractName,
    contractData = contractData,
    owner = owner,
    mac = mac,
    ip = ip,
    contractNumber = contractNumber,
    enabled = true,
    registeredAt = registeredAt?.toDateTime() ?: DateTime.now(),
    lastPaymentAt = lastPaymentAt?.toDateTime() ?: DateTime.now(),
    loans = emptyList(),
    traffic = emptyList(),
    tariff = tariff
)