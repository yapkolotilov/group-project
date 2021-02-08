package me.kolotilov.groupproject.controllers.output

import com.fasterxml.jackson.annotation.JsonProperty
import me.kolotilov.groupproject.domain.models.PaymentStats

/**
 * Статистика по оплате.
 *
 * @param paid Число оплативших пользователей.
 * @param notPaid Число не оплативших пользователей.
 */
data class PaymentStatsDto(
        @JsonProperty("paid")
        val paid: Int,
        @JsonProperty("notPaid")
        val notPaid: Int
)

fun PaymentStats.toPaymentStatsDto() = PaymentStatsDto(
        paid, notPaid
)