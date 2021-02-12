package me.kolotilov.groupproject.domain.services

import me.kolotilov.groupproject.database.models.toTariff
import me.kolotilov.groupproject.database.models.toTariffEntity
import me.kolotilov.groupproject.database.repositories.TariffRepository
import me.kolotilov.groupproject.domain.models.Tariff
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

interface TariffService {

    fun get(id: Int): Tariff

    fun update(tariff: Tariff): Tariff
}

@Service
class TariffServiceImpl : TariffService {

    @Autowired
    private lateinit var tariffRepository: TariffRepository

    override fun get(id: Int): Tariff {
        return tariffRepository.findById(id).get().toTariff()
    }

    override fun update(tariff: Tariff): Tariff {
        return tariffRepository.save(tariff.toTariffEntity()).toTariff()
    }
}