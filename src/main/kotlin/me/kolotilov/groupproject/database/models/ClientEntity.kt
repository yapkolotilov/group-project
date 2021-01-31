package me.kolotilov.groupproject.database.models

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
        @Column(name = "tariff")
        val tariff: String,
        @Column(name = "contractName")
        val contractName: String,
        @Column(name = "contractData")
        val contractData: String,
        @Column(name = "owner")
        val owner: String,
        @OneToMany(targetEntity = TrafficEntity::class, cascade = [CascadeType.ALL])
        val traffic: List<TrafficEntity>,
        @Column(name = "phone")
        val phone: String,
        @Column(name = "email")
        val email: String,
        @Column(name = "mac")
        val mac: String,
        @Column(name = "ip")
        val ip: String,
        @Id
        @GeneratedValue
        @Column(name = "id")
        val id: Int = 0
)
