package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Client
import me.kolotilov.groupproject.domain.models.Tariff
import me.kolotilov.groupproject.utils.toDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "client")
data class ClientEntity(
        @Column(name = "name")
        val name: String,
        @Column(name = "balance")
        val balance: Int,
        @Column(name = "enabled")
        val enabled: Boolean,
        @Column(name = "phone")
        val phone: String,
        @Column(name = "email")
        val email: String,
        @Column(name = "registered_at")
        val registeredAt: Date,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "client_id")
        val loans: List<LoanEntity>,
        @Column(name = "contract_name")
        val contractName: String,
        @Column(name = "contract_data")
        val contractData: String?,
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
        @Column(name = "contract_number")
        val contractNumber: Int = 0,
        @ManyToOne
        @JoinColumn(name = "tariff_name")
        val tariff: TariffEntity
)

fun ClientEntity.toClient() = Client(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDateTime(),
        loans = loans.map { it.toLoan() },
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        ip = ip,
        mac = mac,
        lastPaymentAt = lastPaymentAt.toDateTime(),
        traffic = traffic.map { it.toTraffic() },
        contractNumber = contractNumber,
        tariff = tariff.toTariff()
)

fun Client.toClientEntity() = ClientEntity(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDate(),
        loans = loans.map { it.toLoanEntity() },
        contractName = contractName,
        contractData = contractData,
        owner = owner,
        ip = ip,
        mac = mac,
        lastPaymentAt = lastPaymentAt.toDate(),
        traffic = traffic.map { it.toTrafficEntity() },
        contractNumber = contractNumber,
        tariff = tariff.toTariffEntity()
)