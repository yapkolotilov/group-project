package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDate
import me.kolotilov.groupproject.utils.toDateTime
import me.kolotilov.groupproject.utils.toDuration
import java.util.*

@ApiModel("LoanDto: Кредит пользователю.")
data class LoanDto(
        @ApiModelProperty("Размер в рублях.")
        @JsonProperty("amount")
        val amount: Int,

        @ApiModelProperty("Дата выдачи.")
        @JsonProperty("startDate")
        val startDate: Date,

        @ApiModelProperty("На сколько кредит выдан.")
        @JsonProperty("duration")
        val duration: Date,

        @ApiModelProperty("ID.")
        @JsonProperty("id")
        val id: Int
)

fun Loan.toLoanDto() = LoanDto(
        amount = amount,
        startDate = startDate.toDate(),
        duration = duration.toDate(),
        id = id
)

fun LoanDto.toLoan() = Loan(
        amount = amount,
        startDate = startDate.toDateTime(),
        duration = duration.toDuration(),
        id = id
)