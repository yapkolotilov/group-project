package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.models.toTariff
import me.kolotilov.groupproject.database.models.toTariffEntity
import me.kolotilov.groupproject.database.repositories.TariffRepository
import me.kolotilov.groupproject.domain.models.Tariff
import org.springframework.stereotype.Service

interface TariffService {

    fun clear()

    fun createAll(vararg tariffs: Tariff)

    fun getByName(name: String): Tariff

    fun getAll(): List<Tariff>
}

@Service
private class TariffServiceImpl(
    private val tariffRepository: TariffRepository
) : TariffService {

    override fun getByName(name: String): Tariff {
        return tariffRepository.findByName(name).get().toTariff()
    }

    override fun clear() {
        tariffRepository.deleteAll()
    }

    override fun createAll(vararg tariffs: Tariff) {
        tariffRepository.saveAll(tariffs.map { it.toTariffEntity() })
    }

    override fun getAll(): List<Tariff> {
        return tariffRepository.findAll().map { it.toTariff() }
    }
}