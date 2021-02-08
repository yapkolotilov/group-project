package me.kolotilov.groupproject.domain.models

import org.joda.time.DateTime
import org.joda.time.Duration

/**
 * Кредит пользователю.
 *
 * @param amount Размер в рублях.
 * @param startDate Дата выдачи.
 * @param duration На сколько кредит выдан.
 * @param id ID.
 */
data class Loan(
        val amount: Int,
        val startDate: DateTime,
        val duration: Duration,
        val id: Int = 0
)