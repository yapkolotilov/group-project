package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Client
import org.joda.time.DateTime

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
)

fun CreateClientDto.toClient() = Client(
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
    registeredAt = DateTime.now(),
    lastPaymentAt = DateTime.now(),
    loans = emptyList(),
    traffic = emptyList()
)