package me.kolotilov.groupproject.utils

import org.joda.time.DateTime
import org.joda.time.Interval
import java.util.*

fun Date.toDateTime() = DateTime(this)

fun DateTime.dayInterval(): Interval {
    val day = this
    val startDate = day.withTime(0, 0, 0, 0)
    val endDate = day.withTime(23, 59, 59, 999)
    return Interval(startDate, endDate)
}

fun DateTime.monthInterval(): Interval {
    val day = this
    val startDate = day.withDayOfMonth(0).withTimeAtStartOfDay()
    val endDate = day.withDayOfMonth(day.dayOfMonth().maximumValue)
    return Interval(startDate, endDate)
}

inline fun <A, B, C> Pair<A, B>.replaceFirst(newFirst: (A) -> C): Pair<C, B> {
    return Pair(newFirst(first), second)
}