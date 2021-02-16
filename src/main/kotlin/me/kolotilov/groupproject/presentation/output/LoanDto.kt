package me.kolotilov.groupproject.presentation.output

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