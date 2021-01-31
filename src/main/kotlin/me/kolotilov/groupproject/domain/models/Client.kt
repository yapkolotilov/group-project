package me.kolotilov.groupproject.domain.models

import me.kolotilov.groupproject.database.models.ClientEntity


/**
 * Клиент.
 *
 * @param name ФИО.
 * @param balance Баланс лицевого счёта.
 * @param enabled Подключён ли клиент.
 * @param tariff Тариф.
 * @param contractName Договор.
 * @param contractData Содержимое договора.
 * @param owner На кого заключён договор.
 * @param phone Телефон.
 * @param email Электронная почта.
 * @param mac MAC-адрес.
 * @param ip IP-адрес.
 */
data class Client(
        val name: String,
        val balance: Int,
        val enabled: Boolean,
        val tariff: String,
        val contractName: String,
        val contractData: String,
        val owner: String,
        val traffic: List<Traffic>,
        val phone: String,
        val email: String,
        val mac: String,
        val ip: String,
        val id: Int = 0
)

fun ClientEntity.toClient() = Client(
        name, balance, enabled, tariff, contractName, contractData, owner, traffic.map { it.toTraffic() }, phone, email, mac, ip, id
)

fun Client.toClientEntity() = ClientEntity(
        name, balance, enabled, tariff, contractName, contractData, owner, traffic.map { it.toTrafficEntity() }, phone, email, mac, ip, id
)