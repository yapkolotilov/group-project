package me.kolotilov.groupproject.domain.models

/**
 * Пользователь.
 *
 * @param username Логин.
 * @param password Пароль.
 * @param role Роль.
 */
data class User(
    val username: String,
    val password: String,
    val role: Role
)
