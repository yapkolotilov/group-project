package me.kolotilov.groupproject.database.repositories

import me.kolotilov.groupproject.database.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {

    fun findByUsername(username: String): Optional<UserEntity>
}