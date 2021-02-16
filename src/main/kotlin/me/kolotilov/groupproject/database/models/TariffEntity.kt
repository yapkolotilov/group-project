package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Tariff
import me.kolotilov.groupproject.utils.toDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "tariff")
data class TariffEntity(
        @Column(name = "name")
        val name: String,
        @Column(name = "contract_name")
        val contractName: String,
        @Column(name = "contract_data")
        val contractData: String,
        @Column(name = "owner")
        val owner: String,
        @Column(name = "mac")
        val mac: String,
        @Column(name = "ip")
        val ip: String,
        @Column(name = "last_payment_at")
        val lastPaymentAt: Date,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "traffic_id")
        val traffic: List<TrafficEntity>,
        @Id
        @Column(name = "id")
        val id: Int
)

fun Tariff.toTariffEntity() = TariffEntity(
        name = name,
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        mac = mac,
        ip = ip,
        lastPaymentAt = lastPaymentAt.toDate(),
        traffic = traffic.map { it.toTrafficEntity() },
        id = id
)

fun TariffEntity.toTariff() = Tariff(
        name = name,
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        mac = mac,
        ip = ip,
        lastPaymentAt = lastPaymentAt.toDateTime(),
        traffic = traffic.map { it.toTraffic() },
        id = id
)