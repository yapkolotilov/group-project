package me.kolotilov.groupproject.domain.models

import org.joda.time.DateTime

/**
 * Тариф.
 *
 * @param name Тариф.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param mac MAC-адрес.
 * @param ip IP-адрес.
 * @param id: ID.
 */
data class Tariff(
        val name: String,
        val contractName: String,
        val contractData: String,
        val owner: String,
        val traffic: List<Traffic>,
        val mac: String,
        val ip: String,
        val lastPaymentAt: DateTime,
        val id: Int = 0
)