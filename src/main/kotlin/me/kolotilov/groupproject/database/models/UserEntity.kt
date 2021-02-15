package me.kolotilov.groupproject.database.models

import me.kolotilov.groupproject.domain.models.Role
import me.kolotilov.groupproject.domain.models.User
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "user_table")
data class UserEntity (
        @Id
        @Column(name = "username")
        val username: String,
        @Column(name = "password")
        val password: String,
        @Column(name = "role")
        val role: String
)

fun UserEntity.toUser() = User(
        username = username,
        password = password,
        role = Role.valueOf(role)
)

fun User.toUserEntity() = UserEntity(
        username = username,
        password = password,
        role = role.toString()
)