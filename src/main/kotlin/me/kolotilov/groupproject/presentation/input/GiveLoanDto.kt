package me.kolotilov.groupproject.presentation.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDuration
import org.joda.time.DateTime
import java.util.*
import javax.validation.constraints.Positive

@ApiModel("GiveLoanDto: Модель для предоставления кредита.")
data class GiveLoanDto(
        @ApiModelProperty("Размер кредита в копейках.")
        @JsonProperty("amount")
        @Positive(message = "amount должен быть положительным")
        val amount: Int,

        @ApiModelProperty("На сколько кредит даётся.")
        @JsonProperty("duration")
        val duration: Date
)

fun GiveLoanDto.toLoan() = Loan(
        amount = amount,
        startDate = DateTime.now(),
        duration = duration.toDuration(),
        id = 0
)