package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Tariff
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tariff")
data class TariffEntity(
    @Id
    @Column(name = "name")
    val name: String,
    @Column(name = "speed")
    val speed: Int,
    @Column(name = "price")
    val price: Int
)

fun TariffEntity.toTariff() = Tariff(
    name = name,
    speed = speed,
    price = price
)

fun Tariff.toTariffEntity() = TariffEntity(
    name = name,
    speed = speed,
    price = price
)