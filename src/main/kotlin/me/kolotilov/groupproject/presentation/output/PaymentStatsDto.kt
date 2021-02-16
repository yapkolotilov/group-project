package me.kolotilov.groupproject.presentation.output

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import me.kolotilov.groupproject.domain.models.PaymentStats

/**
 * Статистика по оплате.
 *
 * @param paid Число оплативших пользователей.
 * @param notPaid Число не оплативших пользователей.
 */
@ApiModel("PaymentStatsDto: Статистика по оплате.")
data class PaymentStatsDto(
        @ApiModelProperty("Число оплативших пользователей.")
        @JsonProperty("paid")
        val paid: Int,

        @ApiModelProperty("Число не оплативших пользователей.")
        @JsonProperty("notPaid")
        val notPaid: Int
)

fun PaymentStats.toPaymentStatsDto() = PaymentStatsDto(
        paid = paid,
        notPaid = notPaid
)