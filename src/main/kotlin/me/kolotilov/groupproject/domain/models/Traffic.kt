package me.kolotilov.groupproject.domain.models

import me.kolotilov.groupproject.database.models.TrafficEntity
import org.joda.time.DateTime

/**
 * Траффик пользователя за день.
 *
 * @param date Дата.
 * @param amount Траффик в МБ.
 */
data class Traffic(
        val date: DateTime,
        val amount: Int,
        val id: Int = 0
)

fun TrafficEntity.toTraffic() = Traffic(
        DateTime(date), amount, id
)

fun Traffic.toTrafficEntity() = TrafficEntity(
        date.toDate(), amount, id
)
