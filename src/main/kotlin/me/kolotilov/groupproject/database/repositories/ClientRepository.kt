package me.kolotilov.groupproject.database.repositories

import me.kolotilov.groupproject.database.models.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<ClientEntity, Int> {

    fun findAllByNameContaining(query: String): List<ClientEntity>
}