package me.kolotilov.groupproject.database.repositories

import me.kolotilov.groupproject.database.models.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String> {

    fun getByUsername(username: String): UserEntity
}