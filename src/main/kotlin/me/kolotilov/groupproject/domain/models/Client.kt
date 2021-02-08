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
 * @param id ID.
 */
data class Client(
        val name: String,
        val balance: Int,
        val enabled: Boolean,
        val phone: String,
        val email: String,
        val registeredAt: DateTime,
        val tariffs: List<Tariff>,
        val loans: List<Loan>,
        val id: Int = 0
)