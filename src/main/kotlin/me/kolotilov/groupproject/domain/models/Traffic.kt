package me.kolotilov.groupproject.domain.models

import me.kolotilov.groupproject.database.models.TrafficEntity
import java.util.*

/**
 * Траффик пользователя за день.
 *
 * @param date Дата.
 * @param amount Траффик в МБ.
 */
data class Traffic(
        val date: Date,
        val amount: Int,
        val id: Int = 0
)

fun TrafficEntity.toTraffic() = Traffic(
        date, amount, id
)

fun Traffic.toTrafficEntity() = TrafficEntity(
        date, amount, id
)
