package me.kolotilov.groupproject.database.repositories

import me.kolotilov.groupproject.database.models.TariffEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TariffRepository : JpaRepository<TariffEntity, Int>