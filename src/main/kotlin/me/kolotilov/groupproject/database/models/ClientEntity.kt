package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Client
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
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "client_id")
        val tariffs: List<TariffEntity>,
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Int = 0
)

fun ClientEntity.toClient() = Client(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDateTime(),
        loans = loans.map { it.toLoan() },
        tariffs = tariffs.map { it.toTariff() },
        id = id
)

fun Client.toClientEntity() = ClientEntity(
        name = name,
        balance = balance,
        enabled = enabled,
        phone = phone,
        email = email,
        registeredAt = registeredAt.toDate(),
        loans = loans.map { it.toLoanEntity() },
        tariffs = tariffs.map { it.toTariffEntity() },
        id = id
)