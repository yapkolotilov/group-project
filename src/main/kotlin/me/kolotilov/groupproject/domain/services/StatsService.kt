package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.PaymentStats
import me.kolotilov.groupproject.domain.models.Traffic
import me.kolotilov.groupproject.domain.models.TrafficStats
import me.kolotilov.groupproject.utils.dayInterval
import me.kolotilov.groupproject.utils.monthInterval
import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.Months
import org.springframework.stereotype.Service

interface StatsService {

    fun getTrafficStats(client: Client): TrafficStats

    fun getNewClientsStats(clients: List<Client>): List<Pair<DateTime, Int>>

    fun getPaymentStats(clients: List<Client>): PaymentStats

    fun getPayedClientsStats(clients: List<Client>): List<Pair<Months, Int>>

    fun getNotPayedClientsStats(clients: List<Client>): List<Pair<Months, Int>>

    fun getTotalClientsStats(clients: List<Client>): List<Pair<Months, Int>>
}

@Service
private class StatsServiceImpl : StatsService {

    override fun getTrafficStats(client: Client): TrafficStats {
        var hour = 0
        var fiveHours = 0
        var day = 0
        var week = 0

        val hourInterval = Interval(DateTime.now().minusHours(1), DateTime.now())
        val fiveHoursInterval = Interval(DateTime.now().minusHours(5), DateTime.now())
        val dayInterval = Interval(DateTime.now().minusDays(1), DateTime.now())
        val weekInterval = Interval(DateTime.now().minusWeeks(1), DateTime.now())

        val traffic = client.traffic
        hour += sumTraffic(hourInterval, traffic)
        fiveHours += sumTraffic(fiveHoursInterval, traffic)
        day += sumTraffic(dayInterval, traffic)
        week += sumTraffic(weekInterval, traffic)

        return TrafficStats(hour, fiveHours, day, week)
    }

    override fun getNewClientsStats(clients: List<Client>): List<Pair<DateTime, Int>> {
        val days = DateTime.now().dayOfMonth().maximumValue
        return List(days) { offset ->
            val day = DateTime.now().minusDays(offset)
            val startDate = day.withTime(0, 0, 0, 0)
            val interval = day.dayInterval()
            startDate to clients.count { interval.contains(it.registeredAt) }
        }.sortedBy { it.first }
    }

    override fun getPaymentStats(clients: List<Client>): PaymentStats {
        val interval = DateTime.now().monthInterval()
        val paid = clients.count { client ->
            interval.contains(client.lastPaymentAt)
        }
        val unpaid = clients.size - paid
        return PaymentStats(paid, unpaid)
    }

    override fun getPayedClientsStats(clients: List<Client>): List<Pair<Months, Int>> {
        return getClientStatsImpl(clients.filter { it.payedThisMonth() })
    }

    override fun getNotPayedClientsStats(clients: List<Client>): List<Pair<Months, Int>> {
        return getClientStatsImpl(clients.filter { !it.payedThisMonth() })
    }

    override fun getTotalClientsStats(clients: List<Client>): List<Pair<Months, Int>> {
        return getClientStatsImpl(clients)
    }

    private fun sumTraffic(interval: Interval, traffic: List<Traffic>): Int {
        return traffic.filter { interval.contains(it.date) }.sumBy { it.amount }
    }

    private fun getClientStatsImpl(clients: List<Client>): List<Pair<Months, Int>> {
        val currentMonth = DateTime.now().monthOfYear
        return List(12) {
            val month = currentMonth - it module 12
            Months.months(month) to clients.sumBy { it.monthIncome(month) }
        }.reversed()
    }

    private infix fun Int.module(other: Int): Int {
        return if (this % other <= 0) this + other else this % other
    }

    private fun Client.payedThisMonth(): Boolean {
        return monthIncome(DateTime.now().monthOfYear) > 0
    }

    private fun Client.monthIncome(month: Int): Int {
        val monthStart = DateTime.now().withTimeAtStartOfDay()
            .withDayOfMonth(1).withMonthOfYear(month)
        if (lastPaymentAt.isBefore(monthStart))
            return 0
        return tariff.price
    }
}