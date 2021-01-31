package me.kolotilov.groupproject.database.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "user")
data class UserEntity (
        @Id
        @Column(name = "username")
        val username: String,
        @Column(name = "password")
        val password: String,
        @Column(name = "role")
        val role: String
)