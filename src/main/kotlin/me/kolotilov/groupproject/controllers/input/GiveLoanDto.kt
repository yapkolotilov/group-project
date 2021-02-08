package me.kolotilov.groupproject.controllers.input

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDuration
import org.joda.time.DateTime
import java.util.*
import javax.validation.constraints.Positive

/**
 * Модель для предоставления кредита.
 *
 * @param amount Размер кредита в копейках.
 * @param duration На сколько кредит даётся.
 */
data class GiveLoanDto(
        @JsonProperty("amount")
        @Positive(message = "amount должен быть положительным")
        val amount: Int,
        @JsonProperty("duration")
        val duration: Date
)

fun GiveLoanDto.toLoan() = Loan(
        amount, DateTime.now(), duration.toDuration(), 0
)