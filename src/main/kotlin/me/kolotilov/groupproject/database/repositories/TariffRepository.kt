package me.kolotilov.groupproject.database.repositories

import me.kolotilov.groupproject.database.models.TariffEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TariffRepository : JpaRepository<TariffEntity, String>