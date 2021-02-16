package me.kolotilov.groupproject.domain.models

/**
 * Статистика по оплате.
 *
 * @param paid Оплачено.
 * @param notPaid Не оплачено.
 */
data class PaymentStats(
        val paid: Int,
        val notPaid: Int
)
