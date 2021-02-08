package me.kolotilov.groupproject.controllers.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.Loan
import me.kolotilov.groupproject.utils.toDate
import me.kolotilov.groupproject.utils.toDateTime
import me.kolotilov.groupproject.utils.toDuration
import java.util.*

/**
 * Кредит пользователю.
 *
 * @param amount Размер в рублях.
 * @param startDate Дата выдачи.
 * @param duration На сколько кредит выдан.
 * @param id ID.
 */
data class LoanDto(
        @JsonProperty("amount")
        val amount: Int,
        @JsonProperty("startDate")
        val startDate: Date,
        @JsonProperty("duration")
        val duration: Date,
        @JsonProperty("id")
        val id: Int
)

fun Loan.toLoanDto() = LoanDto(
        amount, startDate.toDate(), duration.toDate(), id
)

fun LoanDto.toLoan() = Loan(
        amount, startDate.toDateTime(), duration.toDuration(), id
)