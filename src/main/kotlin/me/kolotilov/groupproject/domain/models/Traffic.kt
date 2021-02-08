package me.kolotilov.groupproject.domain.models

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

