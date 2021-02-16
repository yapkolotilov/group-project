package me.kolotilov.groupproject.domain.models

import org.joda.time.DateTime


/**
 * Клиент.
 *
 * @param name ФИО.
 * @param balance Баланс лицевого счёта.
 * @param enabled Подключён ли клиент.
 * @param phone Телефон.
 * @param registeredAt Дата регистрации.
 * @param email Электронная почта.
 * @param loans Активные кредиты.
 * @param tariffs Тарифы.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param mac MAC-адрес.
 * @param ip IP-адрес.
 * @param contractNumber ID.
 */
data class Client(
        val name: String,
        val balance: Int,
        val enabled: Boolean,
        val phone: String,
        val email: String,
        val registeredAt: DateTime,
        val loans: List<Loan>,
        val contractName: String,
        val contractData: String?,
        val owner: String,
        val traffic: List<Traffic>,
        val mac: String,
        val ip: String,
        val lastPaymentAt: DateTime,
        val contractNumber: Int
)